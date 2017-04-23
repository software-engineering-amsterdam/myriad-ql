/**
 * Created by alexvanmanen on 24-02-17.
 */

import find from 'lodash/find';
import {Expression} from './expressions/Expression.js';
import {MemoryState} from './memory/MemoryState.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './types/Types.js';

export class ASTValidationVisitor {

    constructor() {
        this.memoryState = new MemoryState();
        this.errors = [];
        this.warnings = [];
        this.labels = [];
    }

    getMemoryState() {
        return this.memoryState;
    }

    visitAST(ast) {
        this.visitForm(ast.getProgram());
        this.visitStatements(ast.getStatements());
    }

    /* TODO, and fix visitor pattern */
    visitForm(form) {
        console.log(form.name);
    }

    visitStatements(statements) {
        for (const statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {
        this.memoryState.set(question.propertyName.name, question.propertyType);

        this.checkDuplicateDeclarations(question);
        this.checkDuplicateLabels(question);

    }


    /**
     * Check duplicate question declarations with different types
     * @param question
     */
    checkDuplicateDeclarations(question) {
        const propertyName = question.getPropertyName();
        const memoryElement = this.memoryState.getElement(propertyName);

        const propertyInMemory = memoryElement !== undefined;

        // TODO check if error should be thrown with a duplicate declaration with the same type
        if (propertyInMemory && question.getPropertyType() !== memoryElement.getType()) {
            this.warnings.push(`Property "${question.getPropertyType()}" is being used with multiple types`);
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

    visitAnswer(answer) {
        //TODO: reference to undefined questions
        this.checkDuplicateLabels(answer);
    }

    visitIfStatement(ifstatement) {
        ifstatement.condition.accept(this);
        //this.visitStatements(ifstatement.ifBody.accept);
    }

    visitIfElseStatement(ifelsestatement) {
        ifelsestatement.condition.accept(this);
    }


    visitPrefixExpression(prefixcondition) {
        if (!(prefixcondition.expression.accept(this) instanceof QLBoolean)) {
            this.errors.push(`Invalid expression. The prefix operator ${prefixcondition.prefix} can not be applied to ${prefixcondition.expression.name}, because it is not a boolean`);
        }
        prefixcondition.expression.accept(this);
    }

    visitExpression(expression) {
        const leftHandType = expression.leftHand.accept(this);
        const rightHandType = expression.rightHand.accept(this);
        const operator = expression.operator;

        if(!leftHandType || !rightHandType){
            return leftHandType;
        } else {
            if (leftHandType.getType() !== rightHandType.getType()) {
                this.errors.push(`Invalid expression. The operator ${operator} can not be applied 
                                to ${expression.leftHand.toString()} [type: ${leftHandType.toString()}] 
                                and ${expression.rightHand.toString()}[type: ${rightHandType.toString()}]. Reason types are different`);
            }

            if(!leftHandType.isValidOperator(operator)){
                this.errors.push(`Invalid expression. The operator ${operator} can not be applied
                            to ${expression.leftHand.toString()} [type: ${leftHandType.toString()}] 
                            and ${expression.rightHand.toString()}[type: ${leftHandType.toString()}]`);
            }

        }
        return expression.getType();
    }

    visitProperty(property) {
        if(property.name === "false" || property.name === "true" ){
            return new QLBoolean();
        } else if (!this.memoryState.getType(property.name)) {
            this.errors.push('Invalid use of property. The property ' + property.name + ' on location: ' + property.location + '  has not been instantiated');
        }
        return this.memoryState.getType(property.name);
    }

    hasDetectedErrors() {
        return !!this.errors.length;
    }
}