import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const NewSchedulePage = () => {
  const navigate = useNavigate();
  const [teamSize, setTeamSize] = useState(0);
  const [persons, setPersons] = useState([]);
  const [shiftTypes, setShiftTypes] = useState([]);
  const [roulements, setRoulements] = useState(1);

  const handlePersonChange = (index, field, value) => {
    const updatedPersons = [...persons];
    updatedPersons[index] = { ...updatedPersons[index], [field]: value };
    setPersons(updatedPersons);
  };

  const addPerson = () => {
    setPersons([...persons, { name: "", contractType: "" }]);
  };

  const removePerson = (index) => {
    const updatedPersons = [...persons];
    updatedPersons.splice(index, 1);
    setPersons(updatedPersons);
  };

  const handleShiftTypeChange = (shift) => {
    setShiftTypes((prev) =>
      prev.includes(shift) ? prev.filter((s) => s !== shift) : [...prev, shift]
    );
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log({
      teamSize,
      persons,
      shiftTypes,
      roulements,
    });
    navigate("/schedule-visualization");
  };

  return (
    <div className="container mt-4">
      {/* <h2 className="text-primary mb-3">Création d'un Nouvel Emploi du Temps</h2> */}
      <form onSubmit={handleSubmit}>
        {/* Team Size */}
        <div className="mb-3">
          <label
            htmlFor="teamSize"
            className="form-label text-danger mb-2"
            style={{ fontSize: "1.1rem", fontWeight: "bold" }}
          >
            Nombre des personnes :
          </label>
          <input
            type="number"
            id="teamSize"
            className="form-control form-control-sm"
            value={teamSize}
            onChange={(e) => setTeamSize(Number(e.target.value))}
            placeholder="Entrez la taille de l'équipe"
            min="0"
          />
        </div>

        {/* Persons */}
        <div className="mb-3">
          <label
            className="form-label text-danger mb-2"
            style={{ fontSize: "1.1rem", fontWeight: "bold" }}
          >
            Personnes :
          </label>
          {persons.map((person, index) => (
            <div key={index} className="d-flex mb-2 align-items-center">
              <input
                type="text"
                className="form-control form-control-sm me-2"
                placeholder="Nom"
                value={person.name}
                onChange={(e) =>
                  handlePersonChange(index, "name", e.target.value)
                }
              />
              <select
                className="form-control form-control-sm me-2"
                value={person.contractType}
                onChange={(e) =>
                  handlePersonChange(index, "contractType", e.target.value)
                }
              >
                <option value="">Type de Contrat</option>
                <option value="100%">100%</option>
                <option value="90%">90%</option>
                <option value="80%">80%</option>
                <option value="70%">70%</option>
                <option value="60%">60%</option>
                <option value="50%">50%</option>
              </select>
              <button
                type="button"
                className="btn btn-danger btn-sm"
                onClick={() => removePerson(index)}
              >
                Supprimer
              </button>
            </div>
          ))}
          <br />
          <button
            type="button"
            className="btn btn-primary btn-sm"
            onClick={addPerson}
          >
            Ajouter une Personne
          </button>
        </div>

        {/* Shift Types */}
        <div className="mb-3">
          <label
            className="form-label text-danger mb-2"
            style={{ fontSize: "1.1rem", fontWeight: "bold" }}
          >
            Types de Shifts :
          </label>
          <div className="d-flex">
            {["Matin", "Après-midi", "Soir"].map((shift) => (
              <div key={shift} className="form-check me-3">
                <input
                  type="checkbox"
                  className="form-check-input"
                  id={`shift-${shift}`}
                  value={shift}
                  checked={shiftTypes.includes(shift)}
                  onChange={() => handleShiftTypeChange(shift)}
                />
                <label
                  className="form-check-label"
                  htmlFor={`shift-${shift}`}
                >
                  {shift}
                </label>
              </div>
            ))}
          </div>
        </div>

        {/* Roulements */}
        <div className="mb-3">
          <label
            htmlFor="roulements"
            className="form-label text-danger mb-2"
            style={{ fontSize: "1.1rem", fontWeight: "bold" }}
          >
            Nombre de Roulements :
          </label>
          <input
            type="number"
            id="roulements"
            className="form-control form-control-sm"
            value={roulements}
            onChange={(e) =>
              setRoulements(Math.max(1, Math.min(4, Number(e.target.value))))
            }
            min="1"
            max="4"
          />
        </div>

        {/* Submit Button */}
        <div className="mt-3">
          <button type="submit" className="btn btn-danger">
            Soumettre
          </button>
        </div>
      </form>
    </div>
  );
};

export default NewSchedulePage;
