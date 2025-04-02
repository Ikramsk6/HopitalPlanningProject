import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { FaPlay, FaCalendarAlt, FaChartBar, FaCheckCircle } from "react-icons/fa";

const ScheduleMonth = () => {
  const month = "March 2025";
  const [shifts, setShifts] = useState([]);  // État pour les shifts récupérés
  const [schedule, setSchedule] = useState([]);
  const [person, setPerson] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activePage, setActivePage] = useState("planning");

  // Fonction pour récupérer les shifts depuis l'API
  const fetchShifts = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/shiftsPostes", {
        headers: { "Cache-Control": "no-cache" }
      });
      if (Array.isArray(response.data)) {
        setShifts(response.data);  // Stocke les shifts dans l'état
      } else {
        throw new Error("Données invalides reçues du serveur.");
      }
    } catch (error) {
      console.error("Erreur lors du chargement des shifts :", error);
      setError("Erreur lors du chargement des shifts.");
    }
  }, []);

  const fetchPerson = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/personnes")
      console.log(response)
      if (Array.isArray(response.data)) {
        setPerson(response.data);  // Stocke les personnes dans l'état
      } else {
        throw new Error("Données invalides reçues du serveur.");
      }
    } catch (error) {
      console.error("Erreur lors du chargement des personnes :", error);
      setError("Erreur lors du chargement des personnes.");
    }
  }, []);

  useEffect(() => {
    fetchShifts();  // Appelle fetchShifts au chargement du composant
    fetchPerson();  // Appelle fetchperson au chargement du composant
  }, [fetchShifts, fetchPerson]);

  // Fonction pour générer les jours du mois
  const generateDays = () => {
    const days = [];
    const weekDays = ["D", "L", "M", "M", "J", "V", "S"];
    const year = 2025;
    const monthIndex = 3; // Mars (les mois commencent à 0 en JavaScript)
    const firstDayOfMonth = new Date(year, monthIndex, 1);
    let startDay = 1;

    // Calcul du premier jour à afficher
    if (firstDayOfMonth.getDay() !== 1) {
      const offset = (firstDayOfMonth.getDay() === 0) ? 1 : (8 - firstDayOfMonth.getDay());
      startDay = 1 + offset;
    }

    const lastDay = new Date(year, monthIndex + 1, 0).getDate(); // Dernier jour du mois (31 mars)

    // Ajout des jours pour ce mois
    for (let day = startDay; day <= lastDay; day++) {
      const date = new Date(year, monthIndex, day);
      const dayLetter = weekDays[date.getDay()];
      days.push({
        dayFormatted: `${dayLetter}${day.toString().padStart(2, "0")}`,
        weekNumber: Math.ceil((day - startDay + 1) / 7)
      });
    }

    // Ajouter les jours du mois suivant si nécessaire (par exemple, du 1er au 6 avril pour la semaine 5)
    const nextMonthStart = new Date(year, monthIndex + 1, 1);
    const remainingDaysInWeek = 7 - days.length % 7; // Calcul du nombre de jours restants pour compléter la dernière semaine
    for (let day = 1; day <= remainingDaysInWeek; day++) {
      const date = new Date(year, monthIndex + 1, day);
      const dayLetter = weekDays[date.getDay()];
      days.push({
        dayFormatted: `${dayLetter}${day.toString().padStart(2, "0")}`,
        weekNumber: Math.ceil((days.length + 1) / 7) // Nouvelle semaine du mois suivant
      });
    }

    return days;
  };

  const daysOfMonth = generateDays();

      const fillRandomData = () => {
        const shiftOptions = shifts.map(shift => shift.name);  // Utilisation des shifts récupérés pour les options

        const newSchedule = schedule.map(person => ({
          ...person,
          weeks: person.weeks.map(() => shiftOptions[Math.floor(Math.random() * shiftOptions.length)]),
        }));
        setSchedule(newSchedule);
      };

      const handleInputChange = (e, rowIndex, dayIndex) => {
        const updatedSchedule = [...schedule];
        updatedSchedule[rowIndex].weeks[dayIndex] = e.target.value.toUpperCase();
        setSchedule(updatedSchedule);
      };

      const calculateShiftSummary = () => {
        return schedule.map(person => {
          const shiftOptions = shifts.map(shift => shift.name); // Dynamique shiftOptions
          const shiftCount = shiftOptions.reduce((acc, shift) => {
            acc[shift] = person.weeks.filter(day => day === shift).length;
            return acc;
          }, {});
          return {name: person.name, ...shiftCount};
        });
      };

      const evaluateConstraints = () => {
        const constraints = [
          {text: "Chaque employé doit avoir au moins un jour de repos (RTT)", respected: true},
          {text: "Un employé ne peut pas avoir plus de 5 RTT dans le mois", respected: false},
          {text: "Il ne doit pas y avoir plus de 3 vacations consécutives", respected: true},
          {text: "Pas plus de 6 jours travaillés consécutifs", respected: true},
          {text: "Respect des préférences horaires des employés", respected: false}
        ];

        // Calcul du score basé sur le pourcentage de contraintes respectées
        const respectedCount = constraints.filter(c => c.respected).length;
        const totalConstraints = constraints.length;
        const score = Math.round((respectedCount / totalConstraints) * 10);

        return {constraints, score};
      };


      return (
          <div className="container mt-3">
            <div className="d-flex justify-content-between align-items-center mb-2">
              <h3 className="text-primary"> {month} </h3>
              {activePage === "planning" && (
                  <button className="btn btn-success" onClick={fillRandomData}>
                    <FaPlay/> Generate
                  </button>
              )}
            </div>

            {activePage === "planning" && (
                <div className="table-responsive">
                  <table className="table table-bordered table-sm text-center" style={{fontSize: "12px"}}>
                    <thead className="table-light">
                    <tr>
                      <th className="px-1">Nom</th>
                      {Array.from(new Set(daysOfMonth.map(day => day.weekNumber))).map((week, index) => (
                          <th key={index} colSpan="7" className="text-center fw-bold">Semaine {week}</th>
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
                    {person.map((person, rowIndex) => (
                        <tr key={rowIndex}>
                          <td className="fw-bold">{`${person.nom} ${person.prenom}`}</td>
                          {daysOfMonth.map((day, dayIndex) => (
                              <td key={dayIndex}>
                                <input
                                    type="text"
                                    className="form-control form-control-sm text-center"
                                    style={{width: "40px", padding: "2px"}}
                                    value={schedule[rowIndex]?.weeks[dayIndex] || ""}  // Affiche vide au départ
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
                  <br/><br/>
                  <table className="table table-bordered table-md text-center"
                         style={{fontSize: "12px", borderCollapse: "collapse"}}>
                    <thead className="table-light">
                    <tr>
                      <th className="px-1">Nom</th>
                      <th colSpan={shifts.length} className="text-center fw-bold bg-dark text-white">Répartition des
                        shifts
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
                              <td key={index} style={{fontWeight: "bold", color: "black", backgroundColor: "white"}}>
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
                  <br/>
                  <h4>Évaluation des contraintes</h4>
                  <ul>
                    {evaluateConstraints().constraints.map((constraint, index) => (
                        <li key={index} style={{color: constraint.respected ? "green" : "red"}}>
                          {constraint.text} {constraint.respected ? "✔️" : "❌"}
                        </li>
                    ))}
                  </ul>
                  <p>
                    <strong>Score global :</strong>{" "}
                    <span style={{fontSize: "18px", color: "#007bff", fontWeight: "bold"}}>
            {evaluateConstraints().score}/10
          </span>
                  </p>
                </div>
            )}

            <nav className="navbar fixed-bottom navbar-light bg-light">
              <div className="container-fluid d-flex justify-content-around">
                <button className={`btn ${activePage === "planning" ? "btn-primary" : "btn-light"}`}
                        onClick={() => setActivePage("planning")}>
                  <FaCalendarAlt/> Planning
                </button>
                <button className={`btn ${activePage === "synthese" ? "btn-primary" : "btn-light"}`}
                        onClick={() => setActivePage("synthese")}>
                  <FaChartBar/> Synthèse
                </button>
                <button className={`btn ${activePage === "evaluation" ? "btn-primary" : "btn-light"}`}
                        onClick={() => setActivePage("evaluation")}>
                  <FaCheckCircle/> Évaluation
                </button>
              </div>
            </nav>
          </div>
      );



};

export default ScheduleMonth;
