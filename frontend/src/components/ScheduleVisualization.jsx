import React, { useState } from "react";
import { FaPlay, FaCalendarAlt, FaChartBar, FaCheckCircle } from "react-icons/fa";

const ScheduleMonth = () => {
  const month = "March 2025";
  const shiftOptions = ["M", "S", "A", "RTT"]; // Shifts possibles

  const generateDays = () => {
    const days = [];
    const weekDays = ["D", "L", "M", "M", "J", "V", "S"];

    for (let day = 1; day <= 31; day++) {
      const date = new Date(2025, 2, day);
      const dayLetter = weekDays[date.getDay()];
      days.push({ dayFormatted: `${dayLetter}${day.toString().padStart(2, "0")}`, weekNumber: Math.ceil(day / 7) });
    }

    return days;
  };

  const daysOfMonth = generateDays();
  const [activePage, setActivePage] = useState("planning");

  const [schedule, setSchedule] = useState([
    { name: "Milan", weeks: Array(31).fill("") },
    { name: "Raphael", weeks: Array(31).fill("") },
    { name: "Ikram", weeks: Array(31).fill("") },
    { name: "Yohanna", weeks: Array(31).fill("") },
    { name: "Ahmed", weeks: Array(31).fill("") },
    { name: "Sidik", weeks: Array(31).fill("") },
    { name: "Clara", weeks: Array(31).fill("") },
  ]);

  const fillRandomData = () => {
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
      const shiftCount = shiftOptions.reduce((acc, shift) => {
        acc[shift] = person.weeks.filter(day => day === shift).length;
        return acc;
      }, {});
      return { name: person.name, ...shiftCount };
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
  
    // Calcul du score basé sur le pourcentage de contraintes respectées
    const respectedCount = constraints.filter(c => c.respected).length;
    const totalConstraints = constraints.length;
    const score = Math.round((respectedCount / totalConstraints) * 10);
  
    return { constraints, score };
  };

  return (
    <div className="container mt-3">
      <div className="d-flex justify-content-between align-items-center mb-2">
        <h3 className="text-primary"> {month} </h3>
        {activePage === "planning" && (
          <button className="btn btn-success" onClick={fillRandomData}>
            <FaPlay /> Generate
          </button>
        )}
      </div>

      {activePage === "planning" && (
        <div className="table-responsive">
          <table className="table table-bordered table-sm text-center" style={{ fontSize: "12px" }}>
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
              {schedule.map((row, rowIndex) => (
                <tr key={rowIndex}>
                  <td className="fw-bold">{row.name}</td>
                  {row.weeks.map((shift, dayIndex) => (
                    <td key={dayIndex}>
                      <input
                        type="text"
                        className="form-control form-control-sm text-center"
                        style={{ width: "40px", padding: "2px" }}
                        value={shift}
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
                <th colSpan={shiftOptions.length} className="text-center fw-bold bg-dark text-white">Répartition des shifts</th>
              </tr>
              <tr>
                <th className="px-1"></th>
                {shiftOptions.map((shift, index) => (
                  <th key={index} className="px-2 bg-dark text-white">{shift}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {calculateShiftSummary().map((row, rowIndex) => (
                <tr key={rowIndex}>
                  <td className="fw-bold">{row.name}</td>
                  {shiftOptions.map((shift, index) => (
                    <td key={index} style={{ fontWeight: "bold", color: "black", backgroundColor: "white" }}>
                      {row[shift]}
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

          <p><strong>Score global :</strong> <span style={{ fontSize: "18px", color: "#007bff", fontWeight: "bold" }}>{evaluateConstraints().score}/10</span></p>

        </div>
      )}

      <nav className="navbar fixed-bottom navbar-light bg-light">
        <div className="container-fluid d-flex justify-content-around">
          <button className={`btn ${activePage === "planning" ? "btn-primary" : "btn-light"}`} onClick={() => setActivePage("planning")}>
            <FaCalendarAlt /> Planning
          </button>
          <button className={`btn ${activePage === "synthese" ? "btn-primary" : "btn-light"}`} onClick={() => setActivePage("synthese")}>
            <FaChartBar /> Synthèse
          </button>
          <button className={`btn ${activePage === "evaluation" ? "btn-primary" : "btn-light"}`} onClick={() => setActivePage("evaluation")}>
            <FaCheckCircle /> Évaluation
          </button>
        </div>
      </nav>
    </div>
  );
};

export default ScheduleMonth;
