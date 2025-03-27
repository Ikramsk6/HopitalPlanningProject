// models/AssocieId.js
class AssocieId {
    constructor(idShift, idMotif) {
        this.idShift = idShift;
        this.idMotif = idMotif;
    }

    equals(other) {
        return other instanceof AssocieId &&
               this.idShift === other.idShift &&
               this.idMotif === other.idMotif;
    }

    hashCode() {
        return `${this.idShift}-${this.idMotif}`;
    }
}

export default AssocieId;
