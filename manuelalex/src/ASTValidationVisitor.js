/**
 * Created by alexvanmanen on 24-02-17.
 */

import find from 'lodash/find';
import {Expression} from './expressions/Expression.js';
import {MemoryState} from './memory/MemoryState.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './types/Types.js';

export class ASTValidationVisitor {

    _reservedBooleanNames = ['true', 'false'];

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
        this.visitForm(ast.getProgram()); // todo maybe remove this
        this.visitStatements(ast.getStatements());
    }


    visitForm(form) {
        /* TODO, and fix visitor pattern */
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

    visitIfStatement(ifStatement) {
        let condition = ifStatement.getCondition();
        condition.accept(this);

        let ifBody = ifStatement.getIfBody();
        for (let statement of ifBody) {
            statement.accept(this);
        }
    }

    visitIfElseStatement(ifElseStatement) {
        let elseBody = ifElseStatement.getElseBody();
        for (let statement of elseBody) {
            statement.accept(this);
        }
        return this.visitIfStatement(ifElseStatement);
    }


    visitPrefixExpression(prefixExpression) {
        let expression = prefixExpression.getExpression();
        if (!(expression.accept(this) instanceof QLBoolean)) {
            this.errors.push(`Invalid expression. The prefix operator ${prefixExpression.getPrefix()} can not be applied to ${expression.getName}, because it is not a boolean`);
        }
        return expression.accept(this); // todo this seems duplicate, see two lines above
    }

    visitExpression(expression) {
        const leftHand = expression.getLeftHand();
        const rightHand = expression.getRightHand();
        const operator = expression.getOperator();

        const leftHandType = leftHand.accept(this);
        const rightHandType = rightHand.accept(this);

        if (!leftHandType || !rightHandType) {
            return leftHandType; // todo what's the purpose if this??
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
        let name = property.getName();
        let location = property.getLocation();

        /* Return QLBoolean for a reserved boolean name */
        if (this._reservedBooleanNames.includes(property.getName())) {
            return new QLBoolean();
        } else if (!this.memoryState.getType(name)) {
            this.errors.push('Invalid use of property. The property ' + name + ' on location: ' + location + '  has not been instantiated');
        }

        return this.memoryState.getType(name);
    }

    hasDetectedErrors() {
        return !!this.errors.length;
    }
}