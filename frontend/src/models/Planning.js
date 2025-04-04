// models/Planning.js
class Planning {
    constructor(idPlanning, nbSemaineMax, nbRoulementMax, nbJoursMaxTravailConsecutifs, nbJoursMinTravailConsecutifs, frequenceWeekend) {
        this.idPlanning = idPlanning;
        this.nbSemaineMax = nbSemaineMax;
        this.nbRoulementMax = nbRoulementMax;
        this.nbJoursMaxTravailConsecutifs = nbJoursMaxTravailConsecutifs;
        this.nbJoursMinTravailConsecutifs = nbJoursMinTravailConsecutifs;
        this.frequenceWeekend = frequenceWeekend;
    }
}

export default Planning;
