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
        return visitor.visitExpression(this);
    }

    evaluate(visitor, memoryState){
        return visitor.evaluateExpression(this, memoryState);
    }


    _throwError(errorText = ''){
        throw new Error(`Error at ${this.location}: ${errorText.toString()}`);
    }
}

export class PrefixExpression {

    constructor(prefix, expression, location){
        this.prefix = prefix;
        this.expression = expression;
        this.location = location;
    }

    getPrefix(){
        return this.prefix;
    }

    getExpression(){
        return this.expression;
    }

    accept(visitor){
        return visitor.visitPrefixExpression(this);
    }

    evaluate(visitor, memoryState){
        return visitor.evaluatePrefixExpression(this, memoryState);
    }

}
