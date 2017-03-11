/**
 * Created by Manuel on 13/02/2017.
 */

//TODO rename to conditional
export class Comparison {

    constructor(leftHand, operator, rightHand, location) {

        this.leftHand = leftHand;
        this.operator = operator;
        this.rightHand = rightHand;

        this.location = location;
    }

    getLeftHand() {
        return this.leftHand;
    }

    getOperator() {
        return this.operator;
    }

    getRightHand() {
        return this.rightHand;
    }

    getLocation() {
        return this.location;
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }

    _throwError(errorText = '') {
        throw new Error(`Error at ${this._location}: ${errorText.toString()}`);
    }

    accept(visitor){
        visitor.visitCondition(this);
    }
}
;