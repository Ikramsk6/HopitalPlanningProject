import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";


const NeedsSetupPage = () => {
    const navigate = useNavigate();
    const jours = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];
    const [shifts, setShifts] = useState([]); // Liste des shifts récupérée
    const [needs, setNeeds] = useState({}); // Besoins à configurer

    // Récupérer les shifts depuis la BDD
    useEffect(() => {
        const fetchShifts = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/shiftsPostes"); // URL API
                setShifts(response.data);
            } catch (error) {
                console.error("Erreur lors de la récupération des shifts :", error);
                alert("Erreur lors de la récupération des shifts.");
            }
        };

        fetchShifts();
    }, []); // Exécuter au montage du composant

    // Initialiser les besoins avec des valeurs par défaut (0) après la récupération des shifts
    useEffect(() => {
        if (shifts.length > 0) {
            const initialNeeds = {};
            shifts.forEach((shift) => {
                jours.forEach((jour, i) => {
                    initialNeeds[`${shift.idShift}-${i}`] = 0; // Initialisation avec 0 pour chaque shift et jour
                });
            });
            setNeeds(initialNeeds);
        }
    }, [shifts]); // Lorsque la liste des shifts change, réinitialiser les besoins


    const onNext = () => {
        navigate("/new-schedule");  // Remplacez par le chemin réel vers la page suivante
    };

    const handleNeedChange = (shiftId, dayIndex, value) => {
        setNeeds(prevNeeds => ({
            ...prevNeeds,
            [`${shiftId}-${dayIndex}`]: value,
        }));
    };

    const handleSubmit = async () => {
        try {
            await axios.post("http://localhost:8080/api/submitNeeds", needs); // URL API pour soumettre les besoins
            alert("Besoins enregistrés avec succès !");
            navigate("/nextPage"); // Naviguer vers une autre page après soumission
        } catch (error) {
            console.error("Erreur lors de la soumission des besoins :", error);
            alert("Erreur lors de la soumission des besoins.");
        }
    };

    return (
        <div>
            <h1>Configuration des besoins</h1>
            <table>
                <thead>
                <tr>
                    <th>Shift</th>
                    {jours.map((jour) => (
                        <th key={jour}>{jour}</th>
                    ))}
                </tr>
                </thead>
                <tbody>
                {shifts.map((shift) => (
                    <tr key={shift.idShift}>
                        <td>{shift.name}</td>
                        {jours.map((jour, i) => (
                            <td key={`${shift.idShift}-${i}`}>
                                <input
                                    type="number"
                                    value={needs[`${shift.idShift}-${i}`] || 0}
                                    onChange={(e) =>
                                        handleNeedChange(shift.idShift, i, Number(e.target.value))
                                    }
                                />
                            </td>
                        ))}
                    </tr>
                ))}
                </tbody>
            </table>
            <button onClick={handleSubmit}>Soumettre</button>
        </div>
    );
};

export default NeedsSetupPage;