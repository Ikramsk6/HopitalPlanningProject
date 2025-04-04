import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const ShiftCreationPage = () => {
    const navigate = useNavigate();
    const [shifts, setShifts] = useState([]);
    const [newShift, setNewShift] = useState({
        tag: "",
        type: "",
        poste: "",
        travail: true,
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetchShifts();
    }, []);

    const fetchShifts = async () => {
        try {
            setLoading(true);
            const response = await axios.get("http://localhost:8080/api/shiftsPostes", {
                headers: { "Cache-Control": "no-cache" },
            });
            console.log("Réponse de l'API shiftsPostes :", response.data); // Ajoutez cette ligne pour afficher la réponse

            setShifts(response.data.reverse());
            setLoading(false);
        } catch (error) {
            console.error("Error fetching shifts:", error);
            setError("Erreur lors du chargement des shifts.");
            setLoading(false);
        }
    };

    const addShift = async () => {
        if (!newShift.tag.trim() || !newShift.type.trim()) {
            alert("Tous les champs obligatoires doivent être remplis.");
            return;
        }
        try {
            setLoading(true);
            const response = await axios.post("http://localhost:8080/api/shiftsPostes", newShift);
            setShifts([response.data, ...shifts]);
            setNewShift({ tag: "", type: "", poste: "", travail: true });
            setLoading(false);
        } catch (error) {
            console.error("Error adding shift:", error);
            setError("Erreur lors de l'ajout du shift.");
            setLoading(false);
        }
    };

    const deleteAllShifts = async () => {
        if (!window.confirm("Êtes-vous sûr de vouloir supprimer tous les shifts ?")) return;
        try {
            setLoading(true);
            await axios.delete("http://localhost:8080/api/shiftsPostes");
            setShifts([]);
            setLoading(false);
        } catch (error) {
            console.error("Error deleting shifts:", error);
            setError("Erreur lors de la suppression des shifts.");
            setLoading(false);
        }
    };

    const deleteShift = async (idShift) => {
        if (!window.confirm("Êtes-vous sûr de vouloir supprimer ce shift ?")) return;
        try {
            setLoading(true);
            await axios.delete(`http://localhost:8080/api/shiftsPostes/${idShift}`);
            setShifts(shifts.filter(shift => shift.idShift !== idShift));
            setLoading(false);
        } catch (error) {
            console.error("Error deleting shift:", error);
            setError("Erreur lors de la suppression du shift.");
            setLoading(false);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setNewShift((prev) => ({ ...prev, [name]: value }));
    };

    return (
        <div className="container mt-4">
            <h3 className="titre" style={{fontSize: '2rem'}}>Création des Shifts</h3>

            <div className="mb-3">
                <input
                    type="text"
                    className="form-control"
                    name="type"
                    value={newShift.type}
                    onChange={handleChange}
                    placeholder="Entrez la description"
                    style={{width: '50%', margin: '0 auto'}}
                />
            </div>
            <div className="mb-3">
                <input
                    type="text"
                    className="form-control"
                    name="tag"
                    value={newShift.tag}
                    onChange={handleChange}
                    placeholder="Entrez le tag"
                    style={{width: '50%', margin: '0 auto'}}
                />
            </div>
            <div className="mb-3">
                <input
                    type="text"
                    className="form-control"
                    name="poste"
                    value={newShift.poste}
                    onChange={handleChange}
                    placeholder="Entrez le poste (facultatif)"
                    style={{width: '50%', margin: '0 auto'}}
                />
            </div>
            <div className="mb-3">
                <select
                    className="form-control"
                    name="travail"
                    value={newShift.travail}
                    onChange={handleChange}
                    style={{width: '50%', margin: '0 auto'}}
                >
                    <option value="true">Oui</option>
                    <option value="false">Non</option>
                </select>
            </div>

            <div className="d-flex justify-content-evenly mb-2">
                <button className="btn btn-success" onClick={() => navigate("/employee-setup")}>
                    Suivant
                </button>
                <button className="btn btn-primary" onClick={addShift}>
                    + Ajouter
                </button>
                <button className="btn btn-danger" onClick={deleteAllShifts}>
                    Supprimer tout les Shifts
                </button>
            </div>

            <ul className="mt-3 list-unstyled">
                {shifts.map((shift) => (
                    <li
                        key={shift.idShift}
                        className="d-flex align-items-center justify-content-between border-bottom py-1"
                    >
                        <div>
                            <strong>{shift.tag}</strong> ({shift.type}) - <em>{shift.poste}</em> -{" "}
                            <span className={shift.travail ? "text-success" : "text-danger"}>
                    {shift.travail ? "Travail" : "Repos"}
                </span>
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
                            onClick={() => deleteShift(shift.idShift)}
                        >
                            ✖
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ShiftCreationPage;
