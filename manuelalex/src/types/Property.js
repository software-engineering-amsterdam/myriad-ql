/**
 * Created by Manuel on 13/03/2017.
 */

export class Property {

    constructor(name, location) {
        this.name = name;
        this.location = location;
    }

    getName() {
        return this.name;
    }

    getLocation() {
        return this.location;
    }

    evaluate(visitor, memoryState = {}) {
        return visitor.evaluateProperty(this, memoryState);
    }

    accept(visitor) {
        return visitor.visitProperty(this);
    }
}