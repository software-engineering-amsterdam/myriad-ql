/**
 * Created by Manuel on 20/02/2017.
 */
export class Operator {

    constructor(options = {}) {
        this.options = options;
    }

    /**
     * Abstract
     * @returns {string}
     */
    toString() {
        return 'Operator';
    }

};