/**
 * Created by Manuel on 24/04/2017.
 */

import {QLBoolean} from '../../types/Types.js';

export class ASTMemoryAllocationVisitor {

    _reservedBooleanNames = ['true', 'false'];

    constructor(memoryState) {
        this.memoryState = memoryState;
    }

    getMemoryState() {
        return this.memoryState;
    }

    visitAST(ast) {
        let statements = ast.getStatements();
        this.visitStatements(statements);
    }

    visitStatements(statements) {
        for (const statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {
        let property = question.getProperty();
        return property.accept(this, question.getPropertyType());
    }

    visitAnswer(answer) {
        let allocation = answer.getAllocation();
        return allocation.accept(this);
    }

    visitAllocation(allocation) {
        let expression = allocation.getExpression();
        return expression.accept(this);
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
        return expression.accept(this);
    }

    visitExpression(expression) {
        const leftHand = expression.getLeftHand();
        const rightHand = expression.getRightHand();
        leftHand.accept(this);
        rightHand.accept(this);
    }

    visitProperty(property, propertyType) {
        let name = property.getName();

        /* Return QLBoolean for a reserved boolean name */
        if (this._reservedBooleanNames.includes(name)) {
            propertyType = new QLBoolean(property.getLocation());
            this.memoryState.set(name, propertyType, property.getName()); // todo name is duplicated per reserved keyword, find out if this is a problem
            return propertyType;
        }

        this.memoryState.set(name, this.memoryState.getType(name) || propertyType);
        return this.memoryState.getType(name);
    }

}