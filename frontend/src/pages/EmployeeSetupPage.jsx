import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const daysOfWeek = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];

const PersonneSetupPage = () => {
    const navigate = useNavigate();
    const [personnes, setPersonnes] = useState([]);
    const [shifts, setShifts] = useState([]);
    const [contracts, setContracts] = useState([]);  // Définir contracts
    const [newPersonne, setNewPersonne] = useState({
        nom: "",
        prenom: "",
        shift: "",
        actif: true,
        preferences: []
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    // Fonction pour récupérer les shifts disponibles
    const fetchShifts = async () => {
        try {
            setLoading(true);
            const response = await axios.get("http://localhost:8080/api/shiftsPostes");
            setShifts(response.data);
            setLoading(false);
        } catch (error) {
            console.error("Erreur lors du chargement des shifts :", error);
            setError("Erreur lors du chargement des shifts.");
            setLoading(false);
        }
    };




    useEffect(() => {
        fetchShifts();
        fetchContracts();
    }, []);

    // Fonction pour ajouter une personne
    const fetchContracts = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/contrats"); // Changer "/api/contracts" en "/api/contrats"
            setContracts(response.data);
        } catch (error) {
            console.error("Erreur lors du chargement des contrats :", error);
            setError("Erreur lors du chargement des contrats.");
        }
    };

    const addPersonne = async () => {
        if (!newPersonne.nom.trim() || !newPersonne.prenom.trim()) {
            alert("Tous les champs obligatoires doivent être remplis.");
            return;
        }

        try {
            const response = await axios.post("http://localhost:8080/api/personnes/create", newPersonne); // Vérifie bien que "/api/personnes" est le bon endpoint
            setPersonnes([response.data, ...personnes]);

            console.log("Réponse du serveur :", response.data); // Affiche la réponse reçue

            setNewPersonne({
                nom: "",
                prenom: "",
                shift: "",
                preferences: [],
                actif: true
            });
            setLoading(false);
        } catch (error) {
            console.error("Erreur lors de l'ajout de la personne :", error);
            setError("Erreur lors de l'ajout de la personne.");
            setLoading(false);
        }
    };


    // Fonction pour ajouter une préférence
    const addPreference = (day, shift, service) => {
        const preference = { day, shift, service };
        if (!newPersonne.preferences.some(p => p.day === day && p.shift === shift && p.service === service)) {
            setNewPersonne({
                ...newPersonne,
                preferences: [...newPersonne.preferences, preference]
            });
        }
    };

    // Fonction pour supprimer une préférence
    const removePreference = (preference) => {
        setNewPersonne({
            ...newPersonne,
            preferences: newPersonne.preferences.filter(p => p !== preference)
        });
    };

    // Fonction pour gérer l'action "Suivant"
    const onNext = () => {
        navigate("/forbidden-shifts");  // Remplacez par le chemin réel vers la page suivante
    };

    return (
        <div className="container mt-4">
            <h3 className="titre" style={{fontSize: '2rem'}}>Création des Personnes</h3>

            <div className="mb-3">
                <input
                    type="text"
                    className="form-control"
                    name="nom"
                    value={newPersonne.nom}
                    onChange={(e) => setNewPersonne({...newPersonne, nom: e.target.value})}
                    placeholder="Entrez le nom"
                    style={{width: '50%', margin: '0 auto'}}
                />
            </div>

            <div className="mb-3">
                <input
                    type="text"
                    className="form-control"
                    name="prenom"
                    value={newPersonne.prenom}
                    onChange={(e) => setNewPersonne({...newPersonne, prenom: e.target.value})}
                    placeholder="Entrez le prénom"
                    style={{width: '50%', margin: '0 auto'}}
                />
            </div>

            <div className="mb-3">
                <select
                    className="form-control"
                    name="contract"
                    value={newPersonne.contract ? newPersonne.contract.idContrat : ""}
                    onChange={(e) => {
                        const selectedContract = contracts.find(contract => contract.idContrat === parseInt(e.target.value));
                        setNewPersonne({...newPersonne, contract: selectedContract});
                    }}
                    style={{width: "50%", margin: "0 auto"}}
                >
                    <option value="">Sélectionner un contrat</option>
                    {contracts.map((contract) => (
                        <option key={contract.idContrat} value={contract.idContrat}>
                            {contract.descriptionContrat} ({contract.pourcentageTravail}%)
                        </option>
                    ))}
                </select>
            </div>

            <div className="mb-3">
                <label>Actif :</label>
                <div className="form-check form-switch">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="activeSwitch"
                        checked={newPersonne.actif}
                        onChange={() => setNewPersonne({...newPersonne, actif: !newPersonne.actif})}
                    />
                    <label className="form-check-label" htmlFor="activeSwitch">
                        {newPersonne.actif ? "Actif" : "Inactif"}
                    </label>
                </div>
            </div>

            <div className="mb-3">
                <label>Préférences :</label>
                {daysOfWeek.map((day) => (
                    <div key={day}>
                        <h5>{day}</h5>
                        {Array.isArray(shifts) && shifts.map((shift, index) => (<button
                                key={index}
                                className="btn btn-info m-1"
                                onClick={() => addPreference(day, shift.type, shift.poste)}  // Utilisez 'type' pour le shift et 'poste' pour le service
                                style={{margin: '5px', padding: '10px 20px', fontSize: '1rem'}}
                            >
                                {shift.type} - {shift.poste || ""} {/* Affiche "Non défini" si 'poste' est vide */}
                            </button>
                        ))}
                    </div>
                ))}
            </div>

            <div className="mb-3">
                <label>Liste des Préférences :</label>
                <ul className="list-unstyled mt-3">
                    {newPersonne.preferences.map((preference, index) => (
                        <li
                            key={index}
                            className="d-flex align-items-center justify-content-between border-bottom py-1"
                        >
                            <div>
                                {`${preference.day} ${preference.shift} - ${preference.service}`}
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
                                onClick={() => removePreference(preference)}
                            >
                                ✖
                            </button>
                        </li>
                    ))}
                </ul>
            </div>


            <div className="mt-4">
                <h4>Liste des personnes :</h4>
                <ul className="list-unstyled">
                    {personnes.map((personne, index) => (
                        <li
                            key={index}
                            className="d-flex align-items-center justify-content-between border-bottom py-1"
                        >
                            <div>
                                {personne.nom} {personne.prenom} - {personne.contract} - {personne.actif ? "Actif" : "Inactif"} -
                                Préférences: {personne.preferences.length}
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
                                onClick={() => setPersonnes(personnes.filter((_, i) => i !== index))}
                            >
                                ✖
                            </button>
                        </li>
                    ))}
                </ul>
            </div>

            <div className="d-flex justify-content-evenly mb-2">
                <button className="btn btn-success" onClick={onNext}>
                    Suivant
                </button>
                <button className="btn btn-primary" onClick={addPersonne}>
                    + Ajouter
                </button>
                <button className="btn btn-danger" onClick={() => setPersonnes([])}>
                    Supprimer tout
                </button>
            </div>
        </div>
    );
};

export default PersonneSetupPage;
