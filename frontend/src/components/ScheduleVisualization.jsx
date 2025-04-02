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
  const[month, setMonth] =useState([])

  const monthText = `${months[selectedMonth]} ${selectedYear}`;

  const [shifts, setShifts] = useState([]); // État pour les shifts récupérés
  const [person, setPerson] = useState([]);
  const [schedule, setSchedule] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activePage, setActivePage] = useState("planning");

  // États pour le modal de configuration des roulements
  const [showModal, setShowModal] = useState(false);
  const [numRoul, setNumRoul] = useState("");
  const [contrat, setContrat] = useState("");
  const [tailleRoul, setTailleRoul] = useState("");

  // Fonction pour récupérer les shifts depuis l'API
  const fetchShifts = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/shiftsPostes", {
        headers: { "Cache-Control": "no-cache" }
      });
      if (Array.isArray(response.data)) {
        setShifts(response.data);
      } else {
        throw new Error("Données invalides reçues du serveur.");
      }
    } catch (error) {
      console.error("Erreur lors du chargement des shifts :", error);
      setError("Erreur lors du chargement des shifts.");
    }
  }, []);

  // Fonction pour récupérer les personnes depuis l'API
  const fetchPerson = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/personnes");
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

  // Génération des jours du mois (incluant les jours du mois suivant pour compléter la dernière semaine)
  const generateDays = () => {
    const days = [];
    const weekDays = ["D", "L", "M", "M", "J", "V", "S"];
    const year = selectedYear;
    const monthIndex = selectedMonth; // Utilisation du mois sélectionné
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

    // Compléter la dernière semaine si nécessaire
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

  // Initialiser le schedule une fois que les personnes sont chargées
  useEffect(() => {
    // Initialiser schedule uniquement si 'person' est non vide
    if (person.length > 0) {
      const initialSchedule = person.map(() => ({
        weeks: new Array(daysOfMonth.length).fill("") // Initialise 'weeks' avec un tableau vide
      }));
      // Mettre à jour le schedule seulement si la valeur change
      setSchedule((prevSchedule) => {
        if (prevSchedule.length !== initialSchedule.length) {
          return initialSchedule;
        }
        return prevSchedule;
      });
    }
  }, [person, daysOfMonth]);  // Mettre à jour schedule uniquement lorsque 'person' ou 'daysO

  // Fonction pour générer des données aléatoires dans le planning
  const fillRandomData = async (contratParam, nbRoulParam, tailleRoulParam) => {
    try {
      // Appel à l'API pour générer le nombre de roulements spécifié
      const response = await axios.post(`http://localhost:8080/api/roulements/generate/${nbRoulParam}`);
      const generatedRoulements = response.data; // Supposons que c'est un tableau de roulements avec planningRepos (tableau d'IDs)
      console.log("Roulements générés :", generatedRoulements);

      // Fonction pour transformer un ID en tag grâce aux shifts récupérés
      const getShiftTagById = (id) => {
        const shift = shifts.find(shift => shift.idShift === id);
        console.log(shift)
        return shift.tag ? shift.tag : "Inconnu";
      };

      // Affectation aléatoire : pour chaque personne, on assigne un roulement au hasard,
      // puis on transforme chaque valeur en tag (ou "Repos" si la valeur vaut -1)
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

  const calculateShiftSummary = () => {
    return schedule.map(personSchedule => {
      const shiftOptions = shifts.map(shift => shift.name);
      const shiftCount = shiftOptions.reduce((acc, shift) => {
        acc[shift] = personSchedule.weeks.filter(day => day === shift).length;
        return acc;
      }, {});
      return { name: personSchedule.name, ...shiftCount };
    });
  };

  const evaluateConstraints = () => {
    const constraints = [
      { text: "Chaque employé doit avoir au moins un jour de repos (RTT)", respected: true },
      { text: "Un employé ne peut pas avoir plus de 5 RTT dans le mois", respected: false },
      { text: "Il ne doit pas y avoir plus de 3 vacations consécutives", respected: true },
      { text: "Pas plus de 6 jours travaillés consécutifs", respected: true },
      { text: "Respect des préférences horaires des employés", respected: false }
    ];

    const respectedCount = constraints.filter(c => c.respected).length;
    const totalConstraints = constraints.length;
    const score = Math.round((respectedCount / totalConstraints) * 10);

    return { constraints, score, summary: calculateShiftSummary() };
  };

  // Fonctions pour le modal
  const handleShow = () => setShowModal(true);
  const handleClose = () => setShowModal(false);
  const handleSubmit = (e) => {
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

  // Ajout d'un state pour le mois sélectionné
  // Vous pouvez également ajouter un state pour l'année si nécessaire





    // Nouvelle fonction : Calcul des besoins par jour
    const calculateDailyCoverage = () => {
      const dailyCoverage = {};

      // Initialiser la structure pour chaque jour
      daysOfMonth.forEach(day => {
        dailyCoverage[day.dayFormatted] = {};
        shifts.forEach(shift => {
          dailyCoverage[day.dayFormatted][shift.name] = 0;
        });
      });

      // Compter les personnes par shift chaque jour
      schedule.forEach((personSchedule, personIndex) => {
        personSchedule.weeks.forEach((shiftTag, dayIndex) => {
          const day = daysOfMonth[dayIndex]?.dayFormatted;
          const shift = shifts.find(s => s.tag === shiftTag);

          if (day && shift) {
            dailyCoverage[day][shift.name]++;
          }
        });
      });

      return dailyCoverage;
    };

    const summaryData = calculateShiftSummary();
    const dailyCoverage = calculateDailyCoverage();



  return (
      <div className="container mt-3">
        <div className="d-flex justify-content-between align-items-center mb-2">
          <div>
            <h3 className="text-primary">{monthText || month}</h3>
            {/* Sélection du mois */}
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
                <FaPlay/> Generate
              </button>
          )}
        </div>

        {/* Modal pour saisir les informations */}
        <Modal show={showModal} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Configuration des Roulements</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={handleSubmit}>
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
              <RBButton
                  variant="primary"
                  onClick={() => fillRandomData(contrat, numRoul, tailleRoul)}
                  className="mt-2"
              >
                Soumettre
              </RBButton>
            </Form>
          </Modal.Body>
        </Modal>

        {activePage === "planning" && (
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
                      <th key={index} className="px-1">
                        {day.dayFormatted}
                      </th>
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
        )}

        {activePage === "synthese" && (
            <div className="table-responsive">
              <br /><br />
              <table className="table table-bordered table-md text-center" style={{ fontSize: "12px", borderCollapse: "collapse" }}>
                <thead className="table-light">
                <tr>
                  <th className="px-1">Nom</th>
                  <th colSpan={shifts.length} className="text-center fw-bold bg-dark text-white">
                    Répartition des shifts
                  </th>
                </tr>
                <tr>
                  <th className="px-1"></th>
                  {shifts.map((shift, index) => (
                      <th key={index} className="px-2 bg-dark text-white">{shift.name}</th>
                  ))}
                </tr>
                </thead>
                <tbody>
                {calculateShiftSummary().map((row, rowIndex) => (
                    <tr key={rowIndex}>
                      <td className="fw-bold">{row.name}</td>
                      {shifts.map((shift, index) => (
                          <td key={index} style={{ fontWeight: "bold", color: "black", backgroundColor: "white" }}>
                            {row[shift.name]}
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
                {evaluateConstraints().constraints.map((constraint, index) => (
                    <li key={index} style={{ color: constraint.respected ? "green" : "red" }}>
                      {constraint.text} {constraint.respected ? "✔️" : "❌"}
                    </li>
                ))}
              </ul>
              <p>
                <strong>Score global :</strong>{" "}
                <span style={{ fontSize: "18px", color: "#007bff", fontWeight: "bold" }}>
              {evaluateConstraints().score}/10
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
