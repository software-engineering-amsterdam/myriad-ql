/**
 * Created by Manuel on 13/02/2017.
 */

module.exports = class Expression {

    constructor(options = {}){
        this.options = options;

        this.term = options.term;
        this.operator = options.operator;
        this.expression = options.expression;

        this._location = options.location;
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }

    _throwError(errorText = ''){
        throw new Error(`Error at ${this._location}: ${errorText.toString()}`);
    }
};