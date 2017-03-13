/**
 * Created by Manuel on 13/02/2017.
 */

export class Expression {

    constructor(leftHand, operator, rightHand, location){
        this.leftHand = leftHand;
        this.operator = operator;
        this.rightHand = rightHand;
        this.location = location;
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

    accept(visitor){
        visitor.visitExpression(this);
    }

    validate() {
        throw new Error('Validate method should have been overwritten');
    }

    evaluate(memoryState){

        let leftValue = this.leftHand.evaluate(memoryState);
        let rightValue = this.rightHand.evaluate(memoryState);

        let leftHandValue = leftValue || undefined;
        let rightHandValue = rightValue || undefined;

        return eval(`${leftHandValue} ${this.operator} ${rightHandValue}`);
    }


    _throwError(errorText = ''){
        throw new Error(`Error at ${this.location}: ${errorText.toString()}`);
    }

}
