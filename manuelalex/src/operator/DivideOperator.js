/**
 * Created by Manuel on 20/02/2017.
 */
const Operator = require('./Operator.js');

module.exports = class DivideOperator extends Operator {
    constructor(){
        super()
    }

    toString(){
        return '/';
    }
};