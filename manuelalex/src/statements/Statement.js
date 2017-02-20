/**
 * Created by Manuel on 13/02/2017.
 */

module.exports = class Statement {

    constructor(options = {}) {
        this.options = options;
    }

    getOptions() {
        return this.options;
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }
};