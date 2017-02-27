/**
 * Created by Manuel on 13/02/2017.
 */

export class Expression {

    constructor(options = {}){
        this.options = options;

        this.leftHand = options.term;
        this.operator = options.operator;
        this.rightHand = options.expression;

        this.location = options.location;
    }

    getLocation(){
        return this.location;
    }

    getLeftHand(){
        return this.leftHand;
    }

    getRightHand(){
        return this.rightHand;
    }

    getOperator(){
        return this.operator;
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }

    _throwError(errorText = ''){
        throw new Error(`Error at ${this._location}: ${errorText.toString()}`);
    }
};