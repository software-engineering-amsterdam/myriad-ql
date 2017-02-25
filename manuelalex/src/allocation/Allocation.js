/**
 * Created by Manuel on 20/02/2017.
 */
export class Allocation {

    constructor(options = {}) {

        this.options = options;

        this.propertyName = options.propertyName;
        this.type = options.type;
        this.expression = options.expression;

        this._location = options.location;

    }

    getOptions() {
        return this.options;
    }

    validate() {

    }
};