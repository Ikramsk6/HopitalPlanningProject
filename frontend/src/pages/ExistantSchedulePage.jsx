import React, {useCallback, useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import Button from "../components/Button";
import axios from "axios";

const ExistantSchedulePage = () => {
  const navigate = useNavigate();
  const [scheduleInfo, setScheduleInfo] = useState("");
  const [tags, setTags] = useState({});
  const [shifts, setShifts] = useState([]);
  const [persons, setPersons] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

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

  const fetchPersons = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/persons", {
        headers: { "Cache-Control": "no-cache" }
      });
      if (Array.isArray(response.data)) {
        setPersons(response.data);
        // Initialiser les tags dynamiquement
        const initialTags = {};
        response.data.forEach(person => {
          initialTags[person.id] = "";
        });
        setTags(initialTags);
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
    fetchPersons();
    setLoading(false);
  }, [fetchShifts, fetchPersons]);

  const handleInputChange = (e) => {
    setScheduleInfo(e.target.value);
  };

  const handleTagChange = (e, personId) => {
    setTags({ ...tags, [personId]: e.target.value });
  };


  return (
      <div className="container mt-4">
        {loading && <p>Chargement des données...</p>}
        {error && <p className="text-danger">{error}</p>}

        {!loading && !error && (
            <>
              {/* Zone de texte pour le fichier */}
              <div className="form-group">
                <label
                    htmlFor="scheduleInfo"
                    className="form-label text-danger mb-2"
                    style={{ fontSize: "1.2rem", fontWeight: "bold" }}
                >
                  Fichier :
                </label>
                <textarea
                    id="scheduleInfo"
                    className="form-control"
                    placeholder="Copier ici le contenu de fichier généré par Chronotime..."
                    value={scheduleInfo}
                    onChange={handleInputChange}
                    rows="5"
                />
              </div>

              {/* Sélection des tags dynamiques */}
              <div className="mt-4">
                <h4 className="text-danger mb-3">Affectation des shifts</h4>
                {persons.length > 0 ? (
                    persons.map((person) => (
                        <div className="form-group row mb-1" key={person.id}>
                          <label htmlFor={`person-${person.id}`} className="col-sm-3 col-form-label">
                            {person.nom} {person.prenom} :
                          </label>
                          <div className="col-sm-9">
                            <select
                                className="form-control form-control-sm"
                                id={`person-${person.id}`}
                                value={tags[person.id] || ""}
                                onChange={(e) => handleTagChange(e, person.id)}
                            >
                              <option value="">Sélectionner un shift...</option>
                              {shifts.map((shift) => (
                                  <option key={shift.id} value={shift.nom}>
                                    {shift.nom}
                                  </option>
                              ))}
                            </select>
                          </div>
                        </div>
                    ))
                ) : (
                    <p>Aucune personne trouvée.</p>
                )}
              </div>

              <Button label="Suivant" onClick={() => navigate("/schedule-visualization")} />
            </>
        )}
      </div>
  );

};

export default ExistantSchedulePage;
