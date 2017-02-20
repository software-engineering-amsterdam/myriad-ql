/**
 * Created by Manuel on 20/02/2017.
 */
const Operator = require('./Operator.js');

module.exports = class MultiplyOperator extends Operator {

    constructor(options = {}) {
        super(options)
    }

    toString() {
        return '*'
    }
};