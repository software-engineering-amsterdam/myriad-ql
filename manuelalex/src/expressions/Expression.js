/**
 * Created by Manuel on 13/02/2017.
 */

module.exports = class Expression {

    constructor(options = {}){
        this.term = options.term;
        this.operator = options.operator;
        this.expression = options.expression;
    }

    /**
     * Abstract
     */
    evaluate(){

    }
};