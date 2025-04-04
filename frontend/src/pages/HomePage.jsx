import React from "react";
import { useNavigate } from "react-router-dom";
import Button from "../components/Button";

const HomePage = () => {
  const navigate = useNavigate();

  return (
    <div className="container text-center mt-5">
      <h1 className="text-primary mb-4">Gestion des plannings hospitaliers</h1>
      <p className="lead mb-4">
        Voulez-vous créer un emploi du temps basé sur un existant ?
      </p>
      <div className="d-flex justify-content-center gap-3">
        <Button
          label="Oui"
          onClick={() => navigate("/existant-schedule")}
          variant="success"
          size="lg"
        />
        <Button
          label="Non"
          onClick={() => navigate("/shift-creation")}
          variant="danger"
          size="lg"
        />
      </div>
      <br />
      <img src="icon.png" alt="" style={{ maxWidth: "500px" }} />
    </div>
    
  );
};

export default HomePage;
