/**
 * Created by Manuel on 24/04/2017.
 */

export class ASTMemoryAllocationVisitor {

    constructor(memoryState) {
        this.memoryState = memoryState;
    }

    getMemoryState() {
        return this.memoryState;
    }

    visitAST(ast) {
        const statements = ast.getStatements();
        this.visitStatements(statements);
    }

    visitStatements(statements) {
        for (let statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {
        const property = question.getProperty();
        return property.accept(this, question.getPropertyType());
    }

    visitAnswer(answer) {
        const allocation = answer.getAllocation();
        return allocation.accept(this);
    }

    visitAllocation(allocation) {
        const expression = allocation.getExpression();
        return expression.accept(this);
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
        for (const statement of elseBody) {
            statement.accept(this);
        }
        return this.visitIfStatement(ifElseStatement);
    }


    visitPrefixExpression(prefixExpression) {
        const expression = prefixExpression.getExpression();
        return expression.accept(this);
    }

    visitExpression(expression) {
        const leftHand = expression.getLeftHand();
        const rightHand = expression.getRightHand();
        leftHand.accept(this);
        rightHand.accept(this);
    }

    visitProperty(property, propertyType) {
        const name = property.getName();
        this.memoryState.set(name, this.memoryState.getType(name) || propertyType);
        return this.memoryState.getType(name);
    }

    visitReservedBooleanWords(){}

}