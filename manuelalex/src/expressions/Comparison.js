/**
 * Created by Manuel on 13/02/2017.
 */

export class Comparison {

    constructor(options = {}){
        this.options = options;

        this.leftHand = options.leftHand;
        this.comparisonOperator = options.comparisonOperator;
        this.rightHand = options.rightHand;

        this._location = options.location;
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }

    _throwError(errorText = ''){
        throw new Error(`Error at ${this._location}: ${errorText.toString()}`);
    }
};