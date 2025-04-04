// models/Roulement.js
class Roulement {
    constructor(idRoulement, tailleRoulement, planningRepos = []) {
        this.idRoulement = idRoulement;
        this.tailleRoulement = tailleRoulement;
        this.planningRepos = planningRepos; // Liste des repos, ne pas persisté dans le backend
    }
}

export default Roulement;
