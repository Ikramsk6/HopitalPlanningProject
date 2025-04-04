// models/NonAffiniteId.js
class NonAffiniteId {
    constructor(idPersonne, idPersonne1) {
        this.idPersonne = idPersonne;
        this.idPersonne1 = idPersonne1;
    }

    equals(other) {
        return other instanceof NonAffiniteId &&
               this.idPersonne === other.idPersonne &&
               this.idPersonne1 === other.idPersonne1;
    }

    hashCode() {
        return `${this.idPersonne}-${this.idPersonne1}`;
    }
}

export default NonAffiniteId;
