// models/SolutionId.js
class SolutionId {
    constructor(idPersonne, idRoulement) {
        this.idPersonne = idPersonne;
        this.idRoulement = idRoulement;
    }

    equals(other) {
        return other instanceof SolutionId &&
               this.idPersonne === other.idPersonne &&
               this.idRoulement === other.idRoulement;
    }

    hashCode() {
        return `${this.idPersonne}-${this.idRoulement}`;
    }
}

export default SolutionId;
