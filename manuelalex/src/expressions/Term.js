/**
 * Created by Manuel on 20/02/2017.
 */
module.exports = class Term {
    constructor(options = {}){
        this.term = options.term;
        this.factor = options.factor;
        this.operator = options.operator;
    }
};