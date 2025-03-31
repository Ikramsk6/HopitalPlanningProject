import React, { useState } from "react";
import { useNavigate } from "react-router-dom";


const NeedsSetupPage = () => {
    const navigate = useNavigate();
    const jours = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];

    // Initialisation des besoins avec 0 pour chaque shift et jour
    const initialNeeds = {};
    ["M", "S", "A", "RTT"].forEach(shift => {
        jours.forEach((jour, i) => {
            initialNeeds[`${shift}-${i}`] = 0;  // 0 par défaut
        });
    });

    const [needs, setNeeds] = useState(initialNeeds);

    const onNext = () => {
        navigate("/new-schedule");  // Remplacez par le chemin réel vers la page suivante
    };

    const handleChange = (shift, day, value) => {
        setNeeds({ ...needs, [`${shift}-${day}`]: value });
    };

    return (
        <div className="max-w-3xl mx-auto p-6 bg-white shadow-md rounded-2xl">
            <h3 className="text-2xl font-semibold text-gray-700 mb-4 text-center">Besoins par Shift</h3>
            <div className="overflow-x-auto">
                <table className="w-full border-collapse border border-gray-300 text-sm">
                    <thead>
                    <tr className="bg-gray-100">
                        <th className="border border-gray-300 p-2">Shift</th>
                        {jours.map((jour, i) => (
                            <th key={i} className="border border-gray-300 p-2">{jour}</th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {["M", "S", "A", "RTT"].map((shift) => (
                        <tr key={shift} className="hover:bg-gray-50">
                            <td className="border border-gray-300 p-2 text-center font-medium">{shift}</td>
                            {jours.map((jour, day) => (
                                <td key={day} className="border border-gray-300 p-2 text-center">
                                    <input
                                        type="number"
                                        className="w-16 p-1 border border-gray-400 rounded text-center"
                                        value={needs[`${shift}-${day}`] || 0}  // Par défaut, affiche 0
                                        onChange={(e) => handleChange(shift, day, e.target.value)}
                                    />
                                </td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>

            <div className="d-flex justify-content-evenly mb-2">
                <button className="btn btn-success" onClick={onNext}>
                    Suivant
                </button>
            </div>
        </div>
    );
};

export default NeedsSetupPage;
