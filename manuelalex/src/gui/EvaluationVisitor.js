/**
 * Created by Manuel on 20/03/2017.
 */

export class EvaluationVisitor {

    evaluateExpression(expression, memoryState) {
        const leftValue = expression.getLeftHand().evaluate(this, memoryState);
        const rightValue = expression.getRightHand().evaluate(this, memoryState);

        const leftHandValue = leftValue || undefined;
        const rightHandValue = rightValue || undefined;

        return eval(`${leftHandValue} ${expression.getOperator()} ${rightHandValue}`);
    }

    evaluatePrefixExpression(prefixExpression, memoryState) {
        const value = prefixExpression.getExpression().evaluate(this, memoryState);
        return eval(`${prefixExpression.getPrefix} ${value}`);
    }


    evaluateProperty(property, memoryState) {
        const value = memoryState.getValue(property.getName()) || undefined;
        return eval(value);
    }
}

