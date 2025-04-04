// models/Contrat.js
class Contrat {
    constructor(idContrat, pourcentageTravail, descriptionContrat, roulement) {
        this.idContrat = idContrat;
        this.pourcentageTravail = pourcentageTravail;
        this.descriptionContrat = descriptionContrat;
        this.roulement = roulement;  // Roulement serait un objet ou ID
    }
}

export default Contrat;
