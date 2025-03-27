// models/Equipe.js
class Equipe {
    constructor(idEquipe, nomEquipe, planning) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.planning = planning; // Planning est un objet ou ID
    }
}

export default Equipe;
