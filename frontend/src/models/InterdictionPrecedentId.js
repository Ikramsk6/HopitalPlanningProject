// models/InterdictionPrecedentId.js
class InterdictionPrecedentId {
    constructor(idShift, idShift1) {
        this.idShift = idShift;
        this.idShift1 = idShift1;
    }

    equals(other) {
        return other instanceof InterdictionPrecedentId && 
               this.idShift === other.idShift &&
               this.idShift1 === other.idShift1;
    }

    hashCode() {
        return `${this.idShift}-${this.idShift1}`;
    }
}

export default InterdictionPrecedentId;
