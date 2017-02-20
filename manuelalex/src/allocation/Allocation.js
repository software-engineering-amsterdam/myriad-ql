/**
 * Created by Manuel on 20/02/2017.
 */
module.exports = class Allocation {

    constructor(options = {}) {

        this.options = options;

        this.propertyName = options.propertyName;
        this.type = options.type;
        this.expression = options.expression;
    }

    getOptions() {
        return this.options;
    }

    validate() {

    }
};