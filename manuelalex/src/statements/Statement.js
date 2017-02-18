/**
 * Created by Manuel on 13/02/2017.
 */

module.exports = class Statement {

    constructor() {
        if (this.getGeneratedCode === undefined) {
            throw new TypeError("Must override method");
        }
    }
};