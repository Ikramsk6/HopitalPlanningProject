import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const NeedsSetupPage = ({ onNext }) => {
  const navigate = useNavigate();
  const [needs, setNeeds] = useState({});

  const jours = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];

  const handleChange = (shift, day, value) => {
    setNeeds({ ...needs, [`${shift}-${day}`]: value });
  };

  return (
    <div className="container mt-4">
      <h3 className="text-primary">Besoins par Shift</h3>
      <table className="table table-bordered text-center">
        <thead>
          <tr>
            <th>Shift</th>
            {jours.map((jour, i) => <th key={i}>{jour}</th>)}
          </tr>
        </thead>
        <tbody>
          {["M", "S", "A", "RTT"].map((shift) => (
            <tr key={shift}>
              <td>{shift}</td>
              {jours.map((jour, day) => (
                <td key={day}>
                  <input 
                    type="number" 
                    className="form-control" 
                    value={needs[`${shift}-${day}`] || ""} 
                    onChange={(e) => handleChange(shift, day, e.target.value)} 
                  />
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
      <button className="btn btn-success" onClick={() => navigate("/schedule-visualization")}>Valider</button>
    </div>
  );
};

export default NeedsSetupPage;
