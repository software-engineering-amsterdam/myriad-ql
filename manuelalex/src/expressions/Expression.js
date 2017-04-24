/**
 * Created by Manuel on 13/02/2017.
 */

import {QLBoolean}  from '../types/Types.js';

export class Expression {

    constructor(leftHand, operator, rightHand, location) {
        this.leftHand = leftHand;
        this.operator = operator;
        this.rightHand = rightHand;
        this.location = location;
    }

    getLocation() {
        return this.location;
    }

    getLeftHand() {
        return this.leftHand;
    }

    getRightHand() {
        return this.rightHand;
    }

    getOperator() {
        return this.operator;
    }

    accept(visitor, ...params) {
        return visitor.visitExpression(this, ...params);
    }

    toString() {
        return `${this.getLeftHand().toString()} ${this.getOperator()} ${this.getRightHand().toString()}`;
    }

    /**
     * LeftHand and RightHand should always have the same type, otherwise an error is thrown in the ASTValidationVisitor.
     * Thus only returning the leftHand type is enough to determine the Expression type. One exception are the boolean operators which
     * can work on non-boolean left and right hand values, these should always have a boolean return type.
     * @returns {*}
     */
    isBooleanType() {
        return (['<', '>', '>=', '<=', '!=', '==', '&&', '||'].includes(this.getOperator()));
    }

    getType(leftHandType){
       return this.isBooleanType() ? QLBoolean : leftHandType;
    }
}

export class PrefixExpression {

    constructor(prefix, expression, location) {
        this.prefix = prefix;
        this.expression = expression;
        this.location = location;
    }

    getPrefix() {
        return this.prefix;
    }

    getExpression() {
        return this.expression;
    }

    accept(visitor, ...params) {
        return visitor.visitPrefixExpression(this, ...params);
    }

    toString() {
        return `${this.getPrefix()} ${this.getExpression()}`;
    }

    getTypeName() {
        return this.leftHand.getTypeName();
    }

}
