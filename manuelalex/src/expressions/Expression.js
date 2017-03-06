/**
 * Created by Manuel on 13/02/2017.
 */

export class Expression {

    constructor(leftHand, operator, rightHand, location){

        this.leftHand = leftHand;
        this.operator = operator;
        this.rightHand = rightHand;
        this.location = location
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

    evaluate(memoryState){
        let leftHandValue = memoryState.getValue(this.leftHand);
        let rightHandValue = memoryState.getValue(this.rightHand);

        return eval(`${leftHandValue} ${this.operator} ${rightHandValue}`);
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }

    _throwError(errorText = ''){
        throw new Error(`Error at ${this._location}: ${errorText.toString()}`);
    }
}