package org.ql.typechecker.visitor;

import org.ql.ast.Form;
import org.ql.ast.expression.BinaryExpression;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.error.UndefinedSymbol;

import java.util.List;

public class UndefinedSymbolVisitor extends AbstractTypeCheckVisitor<Void, Void> {

    private final SymbolTable symbolTable;

    public UndefinedSymbolVisitor(SymbolTable symbolTable) {
        super();
        this.symbolTable = symbolTable;
    }

    @Override
    public Void visitForm(Form form, Void ignore) {
        visitStatements(form.getStatements(), null);
        return null;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, Void ignore) {
        visitExpression(ifThen.getCondition(), null);
        visitStatements(ifThen.getThenStatements(), null);
        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, Void ignore) {
        visitExpression(ifThenElse.getCondition(), null);
        visitStatements(ifThenElse.getThenStatements(), null);
        visitStatements(ifThenElse.getElseStatements(), null);
        return null;
    }

    @Override
    public Void visitQuestion(Question question, Void ignore) {
        if (question.getValue() != null) {
            visitExpression(question.getValue(), null);
        }
        return null;
    }

    @Override
    public Void visitProduct(Product node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitIncrement(Increment node, Void ignore) {
        visitExpression(node.getExpression(), ignore);
        return null;
    }

    @Override
    public Void visitSubtraction(Subtraction node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitDivision(Division node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitAddition(Addition node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitDecrement(Decrement node, Void ignore) {
        visitExpression(node.getExpression(), null);
        return null;
    }

    @Override
    public Void visitNegation(Negation node, Void ignore) {
        visitExpression(node.getExpression(), null);
        return null;
    }

    @Override
    public Void visitNotEqual(NotEqual node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitAnd(LogicalAnd node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitLowerThan(LowerThan node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitGreaterThanOrEqual(GreaterThanOrEqual node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitGroup(Group node, Void ignore) {
        visitExpression(node.getExpression(), null);
        return null;
    }

    @Override
    public Void visitGreaterThan(GreaterThan node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitEquals(Equals node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitLowerThanOrEqual(LowerThanOrEqual node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitOr(LogicalOr node, Void ignore) {
        visitBinaryExpression(node);
        return null;
    }

    @Override
    public Void visitBoolean(BooleanLiteral node, Void ignore) {
        return null;
    }

    @Override
    public Void visitDecimal(DecimalLiteral node, Void ignore) {
        return null;
    }

    @Override
    public Void visitInteger(IntegerLiteral node, Void ignore) {
        return null;
    }

    @Override
    public Void visitString(StringLiteral node, Void ignore) {
        return null;
    }

    @Override
    public Void visitParameter(Parameter parameter, Void ignore) {
        if (!symbolTable.isDeclared(parameter.getId())) {
            issuesStorage.addError(new UndefinedSymbol(parameter.getId()));
        }
        return null;
    }

    private void visitBinaryExpression(BinaryExpression node) {
        visitExpression(node.getLeft(), null);
        visitExpression(node.getRight(), null);
    }
}
