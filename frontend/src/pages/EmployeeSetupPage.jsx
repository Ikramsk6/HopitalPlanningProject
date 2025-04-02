import React, {useState, useEffect, useCallback} from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const daysOfWeek = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];

const PersonneSetupPage = () => {
    const navigate = useNavigate();
    const [personnes, setPersonnes] = useState([]);
    const [shifts, setShifts] = useState([]);
    const [contracts, setContracts] = useState([]);
    const [newPersonne, setNewPersonne] = useState({
        nom: "",
        prenom: "",
        shift: "",
        actif: true,
        preferences: [],
        contract: null
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    // Récupérer les shifts disponibles
    const fetchShifts = useCallback(async () => {
        setLoading(true);
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
        } finally {
            setLoading(false);
        }
    }, []);

    // Récupérer les contrats disponibles
    const fetchContracts = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/contrats");
            console.log(response.data);  // Vérifie la réponse ici
            // Vérifiez que la réponse contient un tableau
            if (Array.isArray(response.data)) {
                setContracts(response.data);
            } else {
                console.error("La réponse ne contient pas un tableau de contrats");
                setError("La réponse ne contient pas un tableau de contrats.");
            }
        } catch (error) {
            console.error("Erreur lors du chargement des contrats :", error);
            setError("Erreur lors du chargement des contrats.");
        }
    };


    useEffect(() => {
        fetchShifts();
        fetchContracts();
    }, []);

    // Ajouter une personne
    const addPersonne = async () => {
        if (!newPersonne.nom.trim() || !newPersonne.prenom.trim()) {
            alert("Tous les champs obligatoires doivent être remplis.");
            return;
        }

        if (!newPersonne.contract || !newPersonne.contract.idContrat) {
            alert("Le contrat doit être sélectionné.");
            return;
        }

        // Créer une copie de newPersonne sans le contrat
        const personneSansContrat = { ...newPersonne, contract: null };

        // Affiche la personne sans le contrat pour déboguer
        console.log(personneSansContrat);

        try {
            setLoading(true);

            // Première requête : création de la personne
            const response1 = await axios.post("http://localhost:8080/api/personnes", personneSansContrat);
            console.log()

            // Récupérer la personne créée à partir de la réponse
            const personneCreee = response1.data;

            // Deuxième requête : création ou mise à jour du contrat
            const response2 = await axios.post("http://localhost:8080/api/contrats", newPersonne.contract);

            // Ajouter le contrat à la personne (mise à jour dans la base de données ou association)
            const contratCree = response2.data;
            console.log("perosnne",personneCreee)
            console.log("contrat",contratCree)
            // Si nécessaire, associer explicitement le contrat à la personne
            // Par exemple, ici tu peux envoyer une nouvelle requête pour associer le contrat à la personne
            // Si l'API permet de modifier la personne après la création pour associer le contrat.
            await axios.put(`http://localhost:8080/api/personnes/${contratCree.idContrat}`, {
                ...personneCreee,
                contract: contratCree
            });

            setPersonnes([personneCreee, ...personnes]);

            // Réinitialiser les champs après ajout
            setNewPersonne({
                nom: "",
                prenom: "",
                shift: "",
                actif: true,
                preferences: [],
                contract: null
            });

            setLoading(false);
        } catch (error) {
            console.error("Erreur lors de l'ajout de la personne :", error);
            setError("Erreur lors de l'ajout de la personne.");
            setLoading(false);
        }
    };


    // Ajouter une préférence
    const addPreference = (day, shift, service) => {
        const preference = { day, shift, service };
        if (!newPersonne.preferences.some(p => p.day === day && p.shift === shift && p.service === service)) {
            setNewPersonne({
                ...newPersonne,
                preferences: [...newPersonne.preferences, preference]
            });
        }
    };

    // Supprimer une préférence
    const removePreference = (preference) => {
        setNewPersonne({
            ...newPersonne,
            preferences: newPersonne.preferences.filter(p => p !== preference)
        });
    };

    const deletePersonne = async (idPersonne) => {
        try {
            // Requête pour supprimer la personne
            await axios.delete(`http://localhost:8080/api/personnes/${idPersonne}`);
            // Mettre à jour la liste des personnes après la suppression
            setPersonnes(personnes.filter(person => person.id !== idPersonne));
        } catch (error) {
            console.error("Erreur lors de la suppression de la personne:", error);
            alert("Une erreur est survenue lors de la suppression.");
        }
    };

    const onNext = () => {
        navigate("/forbidden-shifts");
    };

    const deleteAllPersonnes = async () => {
        try {
            setLoading(true);
            // Suppression de toutes les personnes
            await axios.delete("http://localhost:8080/api/personnes");
            setPersonnes([]); // Réinitialiser la liste des personnes après suppression
            setLoading(false);
        } catch (error) {
            console.error("Erreur lors de la suppression de toutes les personnes:", error);
            setError("Erreur lors de la suppression de toutes les personnes.");
            setLoading(false);
        }
    };

    return (
        <div className="container mt-4">
            <h3 className="titre" style={{fontSize: '2rem'}}>Création des Personnes</h3>

            {/* Formulaire Nom */}
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

            {/* Formulaire Prénom */}
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

            {/* Sélectionner un Contrat */}
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

            {/* Statut Actif */}
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

            {/* Ajouter des Préférences */}
            <div className="mb-3">
                <label>Préférences :</label>
                {daysOfWeek.map((day) => (
                    <div key={day}>
                        <h5>{day}</h5>
                        {shifts.map((shift, index) => (
                            <button
                                key={index}
                                className="btn btn-info m-1"
                                onClick={() => addPreference(day, shift.type, shift.poste)}
                                style={{margin: '5px', padding: '10px 20px', fontSize: '1rem'}}
                            >
                                {shift.type} - {shift.poste || "Non défini"}
                            </button>
                        ))}
                    </div>
                ))}
            </div>

            {/* Affichage des préférences */}
            <div className="mb-3">
                <label>Liste des Préférences :</label>
                <ul className="list-unstyled mt-3">
                    {newPersonne.preferences.map((preference, index) => (
                        <li key={index}
                            className="d-flex align-items-center justify-content-between border-bottom py-1">
                            <div>{`${preference.day} ${preference.shift} - ${preference.service}`}</div>
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

            {/* Liste des personnes */}
            <div className="mt-4">
                <h4>Liste des personnes :</h4>
                <ul className="mt-3 list-unstyled">
                    {personnes.map((person) => (
                        <li key={person.id} className="d-flex align-items-center justify-content-between border-bottom py-1">
                            <div>
                                {person.nom} {person.prenom}
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
                                onClick={() => deletePersonne(person.id)}
                            >
                                ✖
                            </button>
                        </li>
                    ))}
                </ul>
            </div>

            {/* Boutons */}
            <div className="d-flex justify-content-evenly mb-2">
                <button className="btn btn-success" onClick={onNext}>
                    Suivant
                </button>
                <button className="btn btn-primary" onClick={addPersonne}>
                    + Ajouter
                </button>
                <button className="btn btn-danger" onClick={deleteAllPersonnes}>
                    Supprimer toutes les personnes
                </button>
            </div>
        </div>

    );
};

export default PersonneSetupPage;
