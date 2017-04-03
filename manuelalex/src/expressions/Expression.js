/**
 * Created by Manuel on 13/02/2017.
 */
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from '../types/Types';
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

    toString(){
        return `${this.getLeftHand().toString()} ${this.getOperator()} ${this.getRightHand().toString()}`;
    }

    getType(){
        // todo return also the other types
        if(['<', '>', '>=', '<=', '!=', '==', '&&', '||'].includes(this.getOperator())){
            return new QLBoolean();
        } else {
            return new QLMoney;
        }

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

    toString(){
        return this.getPrefix() + this.getExpression().toString();
    }

    getTypeName(){
        return this.leftHand.getTypeName();
    }

}
