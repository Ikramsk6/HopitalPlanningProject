import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { FaPlay, FaCalendarAlt, FaChartBar, FaCheckCircle } from "react-icons/fa";
import { Modal, Form, Button as RBButton } from "react-bootstrap";
import Button from "./Button"; // Votre composant custom Button

const ScheduleMonth = () => {
  const months = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];
  const [selectedMonth, setSelectedMonth] = useState(2); // 0-indexé : 2 = March
  const [selectedYear, setSelectedYear] = useState(2025);
  const [month, setMonth] = useState([]);
  const monthText = `${months[selectedMonth]} ${selectedYear}`;

  const [shifts, setShifts] = useState([]); // Shifts récupérés
  const [person, setPerson] = useState([]); // Personnes récupérées
  const [schedule, setSchedule] = useState([]); // Planning (tableau par personne)
  const [needs, setNeeds] = useState({}); // Besoins par shift et jour
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activePage, setActivePage] = useState("planning");

  // États pour le modal de configuration des roulements
  const [showModal, setShowModal] = useState(false);
  const [numRoul, setNumRoul] = useState("");
  const [contrat, setContrat] = useState("");
  const [tailleRoul, setTailleRoul] = useState("");

  // Récupérer les shifts
  const fetchShifts = useCallback(async () => {
    setLoading(true);
    try {
      const response = await axios.get("http://localhost:8080/api/shiftsPostes", {
        headers: { "Cache-Control": "no-cache" }
      });
      console.log("Données shifts :", response.data);
      if (Array.isArray(response.data)) {
        setShifts(response.data);
      } else {
        throw new Error("Données invalides reçues du serveur.");
      }
    } catch (error) {
      console.error("Erreur lors du chargement des shifts :", error);
      setError("Erreur lors du chargement des shifts.");
    } finally {
      setLoading(false);
    }
  }, []);

  // Récupérer les personnes
  const fetchPerson = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/personnes");
      console.log("Données personnes :", response.data);
      if (Array.isArray(response.data)) {
        setPerson(response.data);
      } else {
        throw new Error("Données invalides reçues du serveur.");
      }
    } catch (error) {
      console.error("Erreur lors du chargement des personnes :", error);
      setError("Erreur lors du chargement des personnes.");
    }
  }, []);

  useEffect(() => {
    fetchShifts();
    fetchPerson();
  }, [fetchShifts, fetchPerson]);

  // Génération des jours du mois (incluant la complétion de la dernière semaine)
  const generateDays = () => {
    const days = [];
    const weekDays = ["D", "L", "M", "M", "J", "V", "S"];
    const year = selectedYear;
    const monthIndex = selectedMonth;
    const firstDayOfMonth = new Date(year, monthIndex, 1);
    let startDay = 1;
    if (firstDayOfMonth.getDay() !== 1) {
      const offset = firstDayOfMonth.getDay() === 0 ? 1 : (8 - firstDayOfMonth.getDay());
      startDay = 1 + offset;
    }
    const lastDay = new Date(year, monthIndex + 1, 0).getDate();
    for (let day = startDay; day <= lastDay; day++) {
      const date = new Date(year, monthIndex, day);
      const dayLetter = weekDays[date.getDay()];
      days.push({
        dayFormatted: `${dayLetter}${day.toString().padStart(2, "0")}`,
        weekNumber: Math.ceil((day - startDay + 1) / 7)
      });
    }
    const remainingDaysInWeek = 7 - (days.length % 7);
    if (remainingDaysInWeek < 7) {
      for (let day = 1; day <= remainingDaysInWeek; day++) {
        const date = new Date(year, monthIndex + 1, day);
        const dayLetter = weekDays[date.getDay()];
        days.push({
          dayFormatted: `${dayLetter}${day.toString().padStart(2, "0")}`,
          weekNumber: Math.ceil((days.length + 1) / 7)
        });
      }
    }
    return days;
  };

  const daysOfMonth = generateDays();

  // Initialisation du planning et des besoins une fois que les personnes sont chargées
  useEffect(() => {
    if (person.length > 0) {
      const initialSchedule = person.map(() => ({
        weeks: new Array(daysOfMonth.length).fill("")
      }));
      setSchedule(initialSchedule);
      const initialNeeds = shifts.reduce((acc, shift) => {
        daysOfMonth.forEach((_, i) => {
          acc[`${shift.idShift}-${i}`] = 0;
        });
        return acc;
      }, {});
      console.log("Initial Needs :", initialNeeds);
      setNeeds(initialNeeds);
    }
  }, [person, daysOfMonth, shifts]);

  // Fonction pour générer des données aléatoires dans le planning
  const fillRandomData = async (contratParam, nbRoulParam, tailleRoulParam) => {
    try {
      const response = await axios.post(`http://localhost:8080/api/roulements/generate/${nbRoulParam}`);
      const generatedRoulements = response.data;
      console.log("Roulements générés :", generatedRoulements);
      const getShiftTagById = (id) => {
        const shift = shifts.find(shift => shift.idShift === id);
        console.log("Shift trouvé pour id", id, ":", shift);
        return shift ? (shift.name || shift.tag || "Inconnu") : "Inconnu";
      };
      const newSchedule = person.map(() => {
        const randomRoulement = generatedRoulements[Math.floor(Math.random() * generatedRoulements.length)];
        return {
          weeks: randomRoulement.planningRepos.map(val => (val === -1 ? "Repos" : getShiftTagById(val)))
        };
      });
      setSchedule(newSchedule);
    } catch (error) {
      console.error("Erreur lors de la génération des roulements :", error);
      setError("Erreur lors de la génération des roulements.");
    }
  };

  const handleInputChange = (e, rowIndex, dayIndex) => {
    const updatedSchedule = [...schedule];
    if (updatedSchedule[rowIndex] && updatedSchedule[rowIndex].weeks) {
      updatedSchedule[rowIndex].weeks[dayIndex] = e.target.value.toUpperCase();
      setSchedule(updatedSchedule);
    } else {
      console.error("Erreur : L'objet weeks n'est pas défini pour cet index de ligne.");
    }
  };

  // Calcul du nombre de personnes présentes par shift et par jour (coverage)
  const calculateDailyCoverage = () => {
    const dailyCoverage = {};
    daysOfMonth.forEach((day, dayIndex) => {
      dailyCoverage[day.dayFormatted] = {};
      shifts.forEach(shift => {
        dailyCoverage[day.dayFormatted][shift.name || shift.tag] = 0;
      });
    });
    schedule.forEach((personSchedule) => {
      personSchedule.weeks.forEach((shiftTag, dayIndex) => {
        const day = daysOfMonth[dayIndex]?.dayFormatted;
        const shift = shifts.find(s => (s.name || s.tag) === shiftTag);
        if (day && shift) {
          dailyCoverage[day][shift.name || shift.tag]++;
        }
      });
    });
    console.log("Daily Coverage :", dailyCoverage);
    return dailyCoverage;
  };

  const dailyCoverage = calculateDailyCoverage();

  // Calcul pour l'onglet synthèse : répartition des shifts par personne
  const calculateShiftSummary = () => {
    return person.map((p, i) => {
      const personSchedule = schedule[i] || { weeks: [] };
      const shiftOptions = shifts.map(shift => (shift.name || shift.tag));
      const shiftCount = shiftOptions.reduce((acc, shiftName) => {
        acc[shiftName] = personSchedule.weeks.filter(day => day === shiftName).length;
        return acc;
      }, {});
      return {
        name: `${p.nom} ${p.prenom}`,
        contract: p.contract ? p.contract.descriptionContrat : "N/A",
        ...shiftCount
      };
    });
  };

  // Fonctions pour le modal
  const handleShow = () => setShowModal(true);
  const handleClose = () => setShowModal(false);
  const handleModalSubmit = (e) => {
    e.preventDefault();
    console.log("Nombre de roulements:", numRoul);
    console.log("Contrat:", contrat);
    console.log("Taille de roulement:", tailleRoul);
    setShowModal(false);
    fillRandomData(contrat, numRoul, tailleRoul);
  };

  const handleMonthChange = (e) => {
    setSelectedMonth(Number(e.target.value));
  };

  return (
      <div className="container mt-3">
        <div className="d-flex justify-content-between align-items-center mb-2">
          <div>
            <h3 className="text-primary">{monthText || month}</h3>
            <Form.Group controlId="monthSelect" className="mt-2">
              <Form.Label>Sélectionnez le mois :</Form.Label>
              <Form.Control as="select" value={selectedMonth} onChange={handleMonthChange}>
                {months.map((m, index) => (
                    <option key={index} value={index}>{m}</option>
                ))}
              </Form.Control>
            </Form.Group>
          </div>
          {activePage === "planning" && (
              <button className="btn btn-success" onClick={handleShow}>
                <FaPlay /> Generate
              </button>
          )}
        </div>

        {/* Modal pour la configuration des roulements */}
        <Modal show={showModal} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Configuration des Roulements</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={handleModalSubmit}>
              <Form.Group className="mb-3" controlId="numRoul">
                <Form.Label>Nombre de roulements</Form.Label>
                <Form.Control
                    type="number"
                    placeholder="Entrez le nombre de roulements"
                    value={numRoul}
                    onChange={(e) => setNumRoul(e.target.value)}
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="contrat">
                <Form.Control
                    type="text"
                    placeholder="Entrez le type de contrat"
                    value={contrat}
                    onChange={(e) => setContrat(e.target.value)}
                />
              </Form.Group>
              <Form.Group className="mb-3" controlId="tailleRoul">
                <Form.Control
                    type="number"
                    placeholder="Entrez la taille de roulement"
                    value={tailleRoul}
                    onChange={(e) => setTailleRoul(e.target.value)}
                />
              </Form.Group>
              <RBButton variant="primary" className="mt-2" type="submit">
                Soumettre
              </RBButton>
            </Form>
          </Modal.Body>
        </Modal>

        {activePage === "planning" && (
            <>
              {/* Tableau de planning (EDT) */}
              <div className="table-responsive">
                <table className="table table-bordered table-sm text-center" style={{ fontSize: "12px" }}>
                  <thead className="table-light">
                  <tr>
                    <th className="px-1">Nom</th>
                    {Array.from(new Set(daysOfMonth.map(day => day.weekNumber))).map((week, index) => (
                        <th key={index} colSpan="7" className="text-center fw-bold">
                          Semaine {week}
                        </th>
                    ))}
                  </tr>
                  <tr>
                    <th className="px-1"></th>
                    {daysOfMonth.map((day, index) => (
                        <th key={index} className="px-1">{day.dayFormatted}</th>
                    ))}
                  </tr>
                  </thead>
                  <tbody>
                  {person.map((p, rowIndex) => (
                      <tr key={rowIndex}>
                        <td className="fw-bold">{`${p.nom} ${p.prenom}`}</td>
                        {daysOfMonth.map((day, dayIndex) => (
                            <td key={dayIndex}>
                              <input
                                  type="text"
                                  className="form-control form-control-sm text-center"
                                  style={{ width: "40px", padding: "2px" }}
                                  value={schedule[rowIndex]?.weeks[dayIndex] || ""}
                                  onChange={(e) => handleInputChange(e, rowIndex, dayIndex)}
                              />
                            </td>
                        ))}
                      </tr>
                  ))}
                  </tbody>
                </table>
              </div>

              {/* Section : Affichage horizontal des besoins et couverture quotidienne */}
              <div className="mt-4">
                <h4>Besoins et couverture quotidienne</h4>
                <table className="table table-bordered table-sm text-center" style={{ fontSize: "12px" }}>
                  <thead className="table-light">
                  <tr>
                    <th>Shift</th>
                    {daysOfMonth.map((day, index) => (
                        <th key={day.dayFormatted}>{day.dayFormatted}</th>
                    ))}
                  </tr>
                  </thead>
                  <tbody>
                  {shifts.filter(s => s.travail === true).map((shift) => (
                      <tr key={shift.idShift}>
                        <td>{shift.tag || shift.name}</td>
                        {daysOfMonth.map((day, dayIndex) => (
                            <td key={day.dayFormatted}>
                              Besoins: {needs[`${shift.idShift}-${dayIndex}`] || 0} <br />
                              Présence: {dailyCoverage[day.dayFormatted] ? dailyCoverage[day.dayFormatted][shift.name || shift.tag] : 0}
                            </td>
                        ))}
                      </tr>
                  ))}
                  </tbody>
                </table>
              </div>
            </>
        )}

        {activePage === "synthese" && (
            <div className="table-responsive">
              <br /><br />
              <table className="table table-bordered table-md text-center" style={{ fontSize: "12px", borderCollapse: "collapse" }}>
                <thead className="table-light">
                <tr>
                  <th className="px-1">Nom</th>
                  <th className="px-1">Contrat</th>
                  <th colSpan={shifts.length} className="text-center fw-bold bg-dark text-white">
                    Répartition des shifts
                  </th>
                </tr>
                <tr>
                  <th className="px-1"></th>
                  <th className="px-1"></th>
                  {shifts.map((shift, index) => (
                      <th key={index} className="px-2 bg-dark text-white">{shift.name || shift.tag}</th>
                  ))}
                </tr>
                </thead>
                <tbody>
                {calculateShiftSummary().map((row, rowIndex) => (
                    <tr key={rowIndex}>
                      <td className="fw-bold">{row.name}</td>
                      <td>{row.contract}</td>
                      {shifts.map((shift, index) => (
                          <td key={index} style={{ fontWeight: "bold", color: "black", backgroundColor: "white" }}>
                            {row[shift.name || shift.tag] || 0}
                          </td>
                      ))}
                    </tr>
                ))}
                </tbody>
              </table>
            </div>
        )}

        {activePage === "evaluation" && (
            <div>
              <br />
              <h4>Évaluation des contraintes</h4>
              <ul>
                {[
                  { text: "Chaque employé doit avoir au moins un jour de repos (RTT)", respected: true },
                  { text: "Un employé ne peut pas avoir plus de 5 RTT dans le mois", respected: false },
                  { text: "Il ne doit pas y avoir plus de 3 vacations consécutives", respected: true },
                  { text: "Pas plus de 6 jours travaillés consécutifs", respected: true },
                  { text: "Respect des préférences horaires des employés", respected: false }
                ].map((constraint, index) => (
                    <li key={index} style={{ color: constraint.respected ? "green" : "red" }}>
                      {constraint.text} {constraint.respected ? "✔️" : "❌"}
                    </li>
                ))}
              </ul>
              <p>
                <strong>Score global :</strong>{" "}
                <span style={{ fontSize: "18px", color: "#007bff", fontWeight: "bold" }}>
              {Math.round((3 / 5) * 10)}/10
            </span>
              </p>
            </div>
        )}

        <nav className="navbar fixed-bottom navbar-light bg-light">
          <div className="container-fluid d-flex justify-content-around">
            <button
                className={`btn ${activePage === "planning" ? "btn-primary" : "btn-light"}`}
                onClick={() => setActivePage("planning")}
            >
              <FaCalendarAlt /> Planning
            </button>
            <button
                className={`btn ${activePage === "synthese" ? "btn-primary" : "btn-light"}`}
                onClick={() => setActivePage("synthese")}
            >
              <FaChartBar /> Synthèse
            </button>
            <button
                className={`btn ${activePage === "evaluation" ? "btn-primary" : "btn-light"}`}
                onClick={() => setActivePage("evaluation")}
            >
              <FaCheckCircle /> Évaluation
            </button>
          </div>
        </nav>
      </div>
  );
};

export default ScheduleMonth;
