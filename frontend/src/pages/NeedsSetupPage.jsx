import React, { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const NeedsSetupPage = () => {
    const navigate = useNavigate();
    const jours = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];
    const [shifts, setShifts] = useState([]);
    const [needs, setNeeds] = useState({});
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);


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

    useEffect(() => {
        fetchShifts();
    }, [fetchShifts]);

    useEffect(() => {
        if (shifts.length > 0) {
            const initialNeeds = shifts.reduce((acc, shift) => {
                jours.forEach((_, i) => {
                    acc[`${shift.idShift}-${i}`] = 0;
                });
                return acc;
            }, {});
            setNeeds(initialNeeds);
        }
    }, [shifts]);

    const onNext = () => {
        navigate("/schedule-visualization");
    };

    const handleNeedChange = (shiftId, dayIndex, value) => {
        setNeeds(prevNeeds => ({
            ...prevNeeds,
            [`${shiftId}-${dayIndex}`]: value,
        }));
    };

    const handleSubmit = async () => {
        try {
            await axios.post("http://localhost:8080/api/submitNeeds", needs);
            alert("Besoins enregistrés avec succès !");
            onNext();
        } catch (error) {
            console.error("Erreur lors de la soumission des besoins :", error);
            alert("Erreur lors de la soumission des besoins.");
        }
    };

    return (
        <div style={{ padding: "20px", textAlign: "center" }}>
            <h1>Configuration des besoins</h1>
            {loading && <p>Chargement des shifts...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}
            {!loading && !error && (
                <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
                    <thead>
                    <tr style={{ backgroundColor: "#f4f4f4" }}>
                        <th style={{ padding: "10px", border: "1px solid #ddd" }}>Shift</th>
                        {jours.map((jour) => (
                            <th key={jour} style={{ padding: "10px", border: "1px solid #ddd" }}>
                                {jour}
                            </th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>

                    {shifts.map((shift) => (
                        <tr key={shift.idShift} style={{ backgroundColor: "#fff", borderBottom: "1px solid #ddd" }}>
                            <td style={{ padding: "10px", border: "1px solid #ddd" }}>{shift.name}</td>
                            {jours.map((jour, i) => (
                                <td key={`${shift.idShift}-${i}`} style={{ padding: "10px", border: "1px solid #ddd" }}>
                                    <input
                                        type="number"
                                        value={needs[`${shift.idShift}-${i}`] || 0}
                                        onChange={(e) =>
                                            handleNeedChange(shift.idShift, i, Number(e.target.value))
                                        }
                                        style={{
                                            width: "60px",
                                            padding: "5px",
                                            textAlign: "center",
                                            borderRadius: "5px",
                                            border: "1px solid #ccc",
                                        }}
                                    />
                                </td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
            <button
                onClick={handleSubmit}
                disabled={loading}
                style={{
                    marginTop: "20px",
                    padding: "10px 20px",
                    backgroundColor: "#007bff",
                    color: "white",
                    border: "none",
                    borderRadius: "5px",
                    cursor: "pointer",
                }}
            >
                Soumettre
            </button>
        </div>
    );
};

export default NeedsSetupPage;