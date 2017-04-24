/**
 * Created by Manuel on 20/03/2017.
 */

import {AbstractVisitor}    from '../../AbstractVisitor.js';

export class EvaluationVisitor extends AbstractVisitor {

    visitExpression(expression, memoryState) {

        const leftValue = expression.getLeftHand().accept(this, memoryState);
        const rightValue = expression.getRightHand().accept(this, memoryState);

        const leftHandValue = leftValue || undefined;
        const rightHandValue = rightValue || undefined;

        return Boolean(eval(`${leftHandValue} ${expression.getOperator()} ${rightHandValue}`));
    }

    visitPrefixExpression(prefixExpression, memoryState) {
        const value = prefixExpression.getExpression().accept(this, memoryState);
        return Boolean(eval(`${prefixExpression.getPrefix()} ${value}`));
    }


    visitProperty(property, memoryState) {
        const value = memoryState.getValue(property.getName()) || undefined;
        return eval(value);
    }

    visitNumbers(number) {
        return eval(number.getValue());
    }

    visitReservedBooleanWords(reservedBooleanWord){
        return Boolean(eval(reservedBooleanWord.getValue()));
    }
}

