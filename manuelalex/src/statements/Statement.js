/**
 * Created by Manuel on 13/02/2017.
 */


export class Statement {

    constructor(location) {
        this.location = location;
    }

    getLocation() {
        return this.location;
    }

    accept() {
        throw new Error('Accept method should have been overwritten');
    }

    _throwError(errorText = '') {
        throw new Error(`Error at ${this.location}: ${errorText.toString()}`);
    }
}