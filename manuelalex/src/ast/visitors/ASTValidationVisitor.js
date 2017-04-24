/**
 * Created by alexvanmanen on 24-02-17.
 */

import find from 'lodash/find';
import {AbstractVisitor} from '../../AbstractVisitor.js';
import {QLBoolean, QLNumber} from '../../types/Types.js';

export class ASTValidationVisitor extends AbstractVisitor{

    constructor(memoryState) {
        super();
        this.memoryState = memoryState;
        this.errors = [];
        this.warnings = [];
        this.labels = [];
    }

    getMemoryState() {
        return this.memoryState;
    }

    visitAST(ast) {
        this.visitStatements(ast.getStatements());
    }

    visitStatements(statements) {
        for (let statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {
        const property = question.getProperty();
        property.accept(this, question.getPropertyType());

        this.checkDuplicateDeclarations(question);
        this.checkDuplicateLabels(question);

    }

    visitAnswer(answer) {
        const allocation = answer.getAllocation();
        this.checkDuplicateLabels(answer);
        return allocation.accept(this);
    }

    visitAllocation(allocation) {
        const expression = allocation.getExpression();
        const returnType = expression.accept(this);

        if (!returnType) {
            this.errors.push(`Invalid allocation. Allocation ${allocation.toString()} does not have a valid return type.`);
        } else if (returnType.constructor.name !== allocation.getType().constructor.name) {
            this.errors.push(`Invalid allocation. The return type should be the same as the type provided, for allocation ${allocation.toString()}.`);
        }
    }

    visitIfStatement(ifStatement) {
        const condition = ifStatement.getCondition();
        condition.accept(this);

        const ifBody = ifStatement.getIfBody();
        for (let statement of ifBody) {
            statement.accept(this);
        }
    }

    visitIfElseStatement(ifElseStatement) {
        const elseBody = ifElseStatement.getElseBody();
        for (let statement of elseBody) {
            statement.accept(this);
        }
        return this.visitIfStatement(ifElseStatement);
    }


    visitPrefixExpression(prefixExpression) {
        const expression = prefixExpression.getExpression();
        const returnType = expression.accept(this);
        if (!(returnType instanceof QLBoolean)) {
            this.errors.push(`Invalid expression. The prefix operator ${prefixExpression.getPrefix()} can not be applied to ${expression.getName}, because it is not a boolean`);
        }
        return returnType;
    }

    visitExpression(expression) {
        const leftHand = expression.getLeftHand();
        const rightHand = expression.getRightHand();
        const operator = expression.getOperator();

        const leftHandType = leftHand.accept(this);
        const rightHandType = rightHand.accept(this);

        if (!leftHandType || !rightHandType) {
            this.errors.push(`Expression ${expression.toString()} does not have a valid leftHand or rightHand type`);
        } else {
            if (leftHandType.getType() !== rightHandType.getType()) {
                this.errors.push(`Invalid expression. The operator ${operator} can not be applied 
                                to ${leftHand.toString()} [type: ${leftHandType.toString()}] 
                                and ${rightHand.toString()}[type: ${rightHandType.toString()}]. Reason types are different`);
            }

            if (!leftHandType.isValidOperator(operator)) {
                this.errors.push(`Invalid expression. The operator ${operator} can not be applied
                            to ${leftHand.toString()} [type: ${leftHandType.toString()}] 
                            and ${rightHand.toString()}[type: ${leftHandType.toString()}]`);
            }
        }
        return expression.getType();

    }

    visitProperty(property) {
        this.checkPropertyIsUndefined(property);
        return this.memoryState.getType(property.getName());
    }

    visitNumbers(){
        return new QLNumber();
    }

    visitReservedBooleanWords(reservedBooleanWord){
        return reservedBooleanWord.getType();
    }

    /**
     * Check duplicate question declarations with different types
     * @param question
     */
    checkDuplicateDeclarations(question) {
        const property = question.getProperty();
        const propertyName = property.getName();
        const memoryElement = this.memoryState.getElement(propertyName);

        const propertyInMemory = memoryElement !== undefined;

        if (propertyInMemory && question.getPropertyType() !== memoryElement.getType()) {
            this.warnings.push(`Property "${propertyName}" is being used with multiple types`);
        }
    }

    checkDuplicateLabels(statement) {
        const localLabel = statement.getLabel();
        const label = find(this.labels, (label) => label.contains(localLabel));

        if (label) {
            this.warnings.push(`Label "${localLabel.getValue()}" is being used multiple times`);
        } else {
            this.labels.push(statement.getLabel());
        }
    }

    checkPropertyIsUndefined(property) {
        const propertyName = property.getName();
        const memoryElement = this.memoryState.getElement(propertyName);
        const propertyHasNoType = memoryElement.getType() === undefined;
        if (propertyHasNoType) {
            this.errors.push(`Property "${property.getName()}" is being used but is not defined with a type.`);
        }
    }

    getErrors() {
        return this.errors;
    }

    getWarnings(){
        return this.warnings;
    }

    hasDetectedErrors() {
        return !!this.errors.length;
    }

    hasDetectedWarnings(){
        return !!this.warnings.length;
    }
}