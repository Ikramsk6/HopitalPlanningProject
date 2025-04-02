import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const ForbiddenShiftsPage = () => {
  const navigate = useNavigate();
  // Array of shift objects (each with idShift and tag)
  const [shifts, setShifts] = useState([]);
  // Selected shift IDs (as strings)
  const [firstShift, setFirstShift] = useState("");
  const [secondShift, setSecondShift] = useState("");
  // List of interdictions (InterdictionPrecedent objects)
  const [interdictions, setInterdictions] = useState([]);

  useEffect(() => {
    fetchShifts();
    fetchInterdictions();
  }, []);

  // Fetch all shifts from /api/shiftsPostes
  const fetchShifts = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/shiftsPostes");
      setShifts(response.data);
    } catch (error) {
      console.error("Error fetching shifts:", error);
      alert("Erreur lors du chargement des shifts.");
    }
  };

  // Fetch all interdictions from /api/interdictionsPrecedents
  const fetchInterdictions = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/interdictionsPrecedents");
      setInterdictions(response.data);
    } catch (error) {
      console.error("Erreur lors de la récupération des interdictions:", error);
    }
  };

  // Save a new interdiction using the embedded composite key structure
  const saveInterdiction = async () => {
    if (!firstShift || !secondShift) {
      alert("Veuillez sélectionner deux shifts.");
      return;
    }
    if (firstShift === secondShift) {
      alert("Les deux shifts ne peuvent pas être identiques.");
      return;
    }
    try {
      // Create a flat payload with idShift and idShift1 as integers
      const payload = {
        id: {
          idShift: parseInt(firstShift, 10),
          idShift1: parseInt(secondShift, 10)
        }
      };
      console.log(payload)
      await axios.post("http://localhost:8080/api/interdictionsPrecedents", payload);
      fetchInterdictions();
      setFirstShift("");
      setSecondShift("");
    } catch (error) {
      console.error("Erreur lors de l'enregistrement de l'interdiction :", error);
      alert("Erreur lors de l'enregistrement de l'interdiction.");
    }
  };


  // Delete an interdiction
  const removeInterdiction = async (idShift, idShift1) => {
    try {
      // Assumes your DELETE endpoint accepts query parameters; adjust if necessary.
      await axios.delete(`http://localhost:8080/api/interdictionsPrecedents?first=${idShift}&second=${idShift1}`);
      fetchInterdictions();
    } catch (error) {
      console.error("Erreur lors de la suppression de l'interdiction :", error);
    }
  };

  return (
      <div className="container mt-4">
        <h3 className="titre" style={{fontSize: "2rem"}}>Shifts Interdits</h3>

        <div className="mb-3">
          <select
              className="form-control"
              value={firstShift}
              onChange={(e) => setFirstShift(e.target.value)}
              style={{width: "50%", margin: "0 auto"}}
          >
            <option value="">Sélectionner le premier shift</option>
            {shifts.map((shift) => (
                <option key={shift.idShift} value={shift.idShift}>
                  {shift.tag}
                </option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <select
              className="form-control"
              value={secondShift}
              onChange={(e) => setSecondShift(e.target.value)}
              style={{width: "50%", margin: "0 auto"}}
          >
            <option value="">Sélectionner le deuxième shift</option>
            {shifts.map((shift) => (
                <option key={shift.idShift} value={shift.idShift}>
                  {shift.tag}
                </option>
            ))}
          </select>
        </div>

        <div className="d-flex justify-content-evenly mb-2">
          <button className="btn btn-success" onClick={() => navigate("/needs-setup")}>
            Suivant
          </button>
          <button className="btn btn-primary" onClick={saveInterdiction}>
            + Ajouter
          </button>
          <button className="btn btn-danger" onClick={() => setInterdictions([])}>
            Supprimer tout
          </button>
        </div>

        {interdictions.length > 0 && (
            <div className="mt-4">
              <h4>Liste des shifts interdits :</h4>
              <ul className="list-unstyled">
                {interdictions.map((item, index) => {
                  const first = shifts.find((s) => s.idShift === item.id.idShift);
                  const second = shifts.find((s) => s.idShift === item.id.idShift1);
                  return (
                      <li
                          key={index}
                          className="d-flex align-items-center justify-content-between border-bottom py-1"
                      >
                        <div>
                          <strong>{first ? first.tag : item.id.idShift}</strong> →{" "}
                          <strong>{second ? second.tag : item.id.idShift1}</strong>
                        </div>
                        <button
                            className="btn p-0 d-flex align-items-center justify-content-center"
                            style={{
                              width: "20px",
                              height: "20px",
                              background: "none",
                              border: "none",
                              color: "black",
                            }}
                            onClick={() => removeInterdiction(item.id.idShift, item.id.idShift1)}
                        >
                          ✖
                        </button>
                      </li>
                  );
                })}
              </ul>
            </div>
        )}
      </div>
  );
};
export default ForbiddenShiftsPage;
