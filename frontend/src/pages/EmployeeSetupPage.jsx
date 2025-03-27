import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const EmployeeSetupPage = ({ onNext }) => {
  const navigate = useNavigate();
  const [employees, setEmployees] = useState([]);
  const [newEmployee, setNewEmployee] = useState({ 
    name: "", 
    contract: "100%", 
    preferences: [] // Ce champ sera conservé pour l'affichage, même s'il n'est pas envoyé au backend
  });

  // Récupérer tous les employés depuis le backend
  const getAllEmployees = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/personnes");
      setEmployees(response.data);
    } catch (error) {
      console.error("Error fetching employees:", error);
      alert("Erreur lors du chargement des employés.");
    }
  };

  useEffect(() => {
    getAllEmployees();
  }, []);

  // Fonction pour ajouter un employé
  const addEmployee = async () => {
    if (newEmployee.name) {
      const employeeData = {
        nom: newEmployee.name,
        prenom: "", 
        actif: true,
        id_contrat: 1, // Utilisation de l'ID du contrat directement
      };
  
      try {
        const response = await axios.post("http://localhost:8080/api/personnes", employeeData);
        if (response.status === 201 || response.status === 200) {
          getAllEmployees();
          setNewEmployee({ name: "", contract: "100%", preferences: [] });
        }
      } catch (error) {
        console.error("Error adding employee:", error);
        alert("Erreur lors de l'ajout de l'employé. Vérifiez les relations en base.");
      }
    }
  };
  

  // Fonctions pour ajouter ou supprimer une préférence (elles restent en gestion locale)
  const addPreference = (preference) => {
    if (preference && !newEmployee.preferences.includes(preference)) {
      setNewEmployee({ 
        ...newEmployee, 
        preferences: [...newEmployee.preferences, preference] 
      });
    }
  };

  const removePreference = (preference) => {
    setNewEmployee({ 
      ...newEmployee, 
      preferences: newEmployee.preferences.filter(p => p !== preference) 
    });
  };

  const daysAndShifts = [
    { value: "Lundi M", label: "Lundi M (Matin)" },
    { value: "Lundi S", label: "Lundi S (Soir)" },
    { value: "Lundi A", label: "Lundi A (Après-midi)" },
    { value: "Mardi M", label: "Mardi M (Matin)" },
    { value: "Mardi S", label: "Mardi S (Soir)" },
    { value: "Mardi A", label: "Mardi A (Après-midi)" },
    { value: "Mercredi M", label: "Mercredi M (Matin)" },
    { value: "Mercredi S", label: "Mercredi S (Soir)" },
    { value: "Mercredi A", label: "Mercredi A (Après-midi)" },
    { value: "Jeudi M", label: "Jeudi M (Matin)" },
    { value: "Jeudi S", label: "Jeudi S (Soir)" },
    { value: "Jeudi A", label: "Jeudi A (Après-midi)" },
    { value: "Vendredi M", label: "Vendredi M (Matin)" },
    { value: "Vendredi S", label: "Vendredi S (Soir)" },
    { value: "Vendredi A", label: "Vendredi A (Après-midi)" },
    { value: "Samedi M", label: "Samedi M (Matin)" },
    { value: "Samedi S", label: "Samedi S (Soir)" },
    { value: "Samedi A", label: "Samedi A (Après-midi)" },
    { value: "Dimanche M", label: "Dimanche M (Matin)" },
    { value: "Dimanche S", label: "Dimanche S (Soir)" },
    { value: "Dimanche A", label: "Dimanche A (Après-midi)" }
  ];

  return (
    <div className="container mt-4">
      <h3 className="text-primary">Ajout des Personnes</h3>
      
      <div className="mb-3">
        <label>Nom :</label>
        <input 
          type="text" 
          className="form-control" 
          value={newEmployee.name} 
          onChange={(e) => setNewEmployee({ ...newEmployee, name: e.target.value })} 
        />
      </div>
      
      <div className="mb-3">
        <label>Contrat :</label>
        <select 
          className="form-control" 
          value={newEmployee.contract} 
          onChange={(e) => setNewEmployee({ ...newEmployee, contract: e.target.value })}
        >
          <option value="100%">100%</option>
          <option value="90%">90%</option>
          <option value="80%">80%</option>
          <option value="70%">70%</option>
          <option value="60%">60%</option>
          <option value="50%">50%</option>
        </select>
      </div>

      <div className="mb-3">
        <label>Préférences :</label>
        <select 
          className="form-control" 
          onChange={(e) => addPreference(e.target.value)}
        >
          <option value="">Sélectionner un shift</option>
          {daysAndShifts.map((option, index) => (
            <option key={index} value={option.value}>{option.label}</option>
          ))}
        </select>
      </div>

      <div className="mb-3">
        <label>Liste des Préférences :</label>
        <ul className="list-group">
          {newEmployee.preferences.map((preference, index) => (
            <li key={index} className="list-group-item">
              {preference} 
              <button 
                className="btn btn-danger btn-sm float-end" 
                onClick={() => removePreference(preference)}
              >
                Supprimer
              </button>
            </li>
          ))}
        </ul>
      </div>

      <button className="btn btn-primary me-2" onClick={addEmployee}>+ Ajouter</button>
      <button className="btn btn-success" onClick={() => navigate("/forbidden-shifts")}>Suivant</button>

      <ul className="mt-3">
        {employees.map((emp) => (
          <li key={emp.idPersonne}>
            {emp.nom} ({emp.contrat ? emp.contrat.idContrat : "N/A"}) - Préférences: {emp.preferences ? emp.preferences.join(', ') : "aucune"}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default EmployeeSetupPage;
