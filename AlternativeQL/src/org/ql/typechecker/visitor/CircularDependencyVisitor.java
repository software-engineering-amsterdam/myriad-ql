package org.ql.typechecker.visitor;

import org.ql.ast.Form;
import org.ql.ast.Identifier;
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
import org.ql.typechecker.issues.IssuesStorage;
import org.ql.typechecker.circular_dependencies.CircularDependenciesResolver;
import org.ql.typechecker.circular_dependencies.DependencyPair;
import org.ql.typechecker.issues.error.CircularDependency;

import java.util.List;

public class CircularDependencyVisitor extends AbstractTypeCheckVisitor<Void, Identifier> {

    private final CircularDependenciesResolver circularDependenciesResolver;

    public CircularDependencyVisitor() {
        super();
        circularDependenciesResolver = new CircularDependenciesResolver();
    }

    @Override
    public Void visitProduct(Product node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitIncrement(Increment node, Identifier questionId) {
        visitExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitSubtraction(Subtraction node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitDivision(Division node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitAddition(Addition node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitDecrement(Decrement node, Identifier questionId) {
        visitExpression(node.getExpression(), questionId);
        return null;
    }

    @Override
    public Void visitNegation(Negation node, Identifier questionId) {
        visitExpression(node.getExpression(), questionId);
        return null;
    }

    @Override
    public Void visitNotEqual(NotEqual node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitAnd(LogicalAnd node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitLowerThan(LowerThan node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitGreaterThanOrEqual(GreaterThanOrEqual node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitGroup(Group node, Identifier questionId) {
        visitExpression(node.getExpression(), questionId);
        return null;
    }

    @Override
    public Void visitGreaterThan(GreaterThan node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitEquals(Equals node, Identifier questionId) {
        visitBinaryExpression(node, questionId);
        return null;
    }

    @Override
    public Void visitLowerThanOrEqual(LowerThanOrEqual lowerThanOrEqual, Identifier questionId) {
        visitBinaryExpression(lowerThanOrEqual, questionId);
        return null;
    }

    @Override
    public Void visitOr(LogicalOr logicalOr, Identifier questionId) {
        visitBinaryExpression(logicalOr, questionId);
        return null;
    }

    @Override
    public Void visitBoolean(BooleanLiteral node, Identifier ignore) {
        return null;
    }

    @Override
    public Void visitDecimal(DecimalLiteral node, Identifier ignore) {
        return null;
    }

    @Override
    public Void visitInteger(IntegerLiteral node, Identifier ignore) {
        return null;
    }

    @Override
    public Void visitString(StringLiteral node, Identifier ignore) {
        return null;
    }

    @Override
    public Void visitParameter(Parameter parameter, Identifier questionId) {

        circularDependenciesResolver.register(new DependencyPair(parameter.getId(), questionId));
        return null;
    }

    @Override
    public Void visitForm(Form form, Identifier ignore) {
        visitStatements(form.getStatements(), null);
        checkForCircularDependencies();

        return null;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, Identifier ignore) {
        visitStatements(ifThen.getThenStatements(), null);
        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, Identifier ignore) {
        visitStatements(ifThenElse.getThenStatements(), null);
        visitStatements(ifThenElse.getElseStatements(), null);
        return null;
    }

    @Override
    public Void visitQuestion(Question question, Identifier questionId) {
        if (question.getValue() != null) {
            visitExpression(question.getValue(), question.getId());
        }
        return null;
    }

    private void checkForCircularDependencies() {
        for (DependencyPair pair : circularDependenciesResolver.circularDependencies()) {
            issuesStorage.addError(new CircularDependency(pair));
        }
    }

    private void visitBinaryExpression(BinaryExpression node, Identifier questionId) {
        visitExpression(node.getLeft(), questionId);
        visitExpression(node.getRight(), questionId);
    }
}
