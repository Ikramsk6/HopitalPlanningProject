// models/Personne.js
class Personne {
    constructor(idPersonne, nom, prenom, actif, contrat) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.actif = actif;
        this.contrat = contrat;  // Ce serait un objet Contrat
    }
}

export default Personne;
