import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "../components/Button";

const ExistantSchedulePage = () => {
  const navigate = useNavigate();
  const [scheduleInfo, setScheduleInfo] = useState("");
  const [tags, setTags] = useState({
    RTT: "",
    M: "",
    S: "",
    A: "",
  });

  const handleInputChange = (e) => {
    setScheduleInfo(e.target.value);
  };

  const handleTagChange = (e, tag) => {
    setTags({ ...tags, [tag]: e.target.value });
  };

  const tagOptions = [
    "Matin",
    "Soir",
    "Surveillance",
    "Après-midi",
    "Repos",
    "Vacances",
    "Absence autorisée",
  ];

  return (
    <div className="container mt-4">
      <div className="form-group">
        <label htmlFor="scheduleInfo" className="form-label text-danger mb-2" style={{ fontSize: "1.2rem", fontWeight: "bold" }}>Fichier :</label>

        <textarea
          id="scheduleInfo"
          className="form-control"
          placeholder="Copier ici le contenu de fichier genere par chronotime..."
          value={scheduleInfo}
          onChange={handleInputChange}
          rows="5"
        />
      </div>

      <div className="mt-4">
        <h4 className="text-danger mb-3">Tags</h4>
        {Object.keys(tags).map((tag) => (
          <div className="form-group row mb-1" key={tag}>
            <label htmlFor={tag} className="col-sm-3 col-form-label">{tag} :</label>
            <div className="col-sm-9">
              <input
                type="text"
                className="form-control form-control-sm"
                list={`${tag}-options`}
                id={tag}
                value={tags[tag]}
                onChange={(e) => handleTagChange(e, tag)}
                placeholder="Choisir ou saisir..."
              />
              <datalist id={`${tag}-options`}>
                {tagOptions.map((option, index) => (
                  <option key={index} value={option} />
                ))}
              </datalist>
            </div>
          </div>
        ))}
      </div>

      <Button label="Suivant" onClick={() => navigate("/schedule-visualization")} />
    </div>
  );
};

export default ExistantSchedulePage;
