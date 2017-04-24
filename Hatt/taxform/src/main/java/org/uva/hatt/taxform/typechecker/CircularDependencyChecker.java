package org.uva.hatt.taxform.typechecker;

import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.FormVisitor;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.ExpressionVisitor;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.typechecker.messages.Messages;
import org.uva.hatt.taxform.typechecker.messages.error.CyclicDependency;

import java.util.*;

public class CircularDependencyChecker implements FormVisitor<Void>, ItemVisitor<Void>, ExpressionVisitor<Void> {

    private final Map<java.lang.String, List<java.lang.String>> dependencies;
    private final Messages messages;
    private Question question;

    CircularDependencyChecker(Messages messages) {
        this.messages = messages;
        dependencies = new HashMap<>();
    }

    @Override
    public Void visit(Addition addition) {
        addition.getLhs().accept(this);
        addition.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(Division division) {
        division.getLhs().accept(this);
        division.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(Equal equal) {
        equal.getLhs().accept(this);
        equal.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan) {
        greaterThan.getLhs().accept(this);
        greaterThan.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(GreaterThanOrEqual greaterThanOrEqual) {
        greaterThanOrEqual.getLhs().accept(this);
        greaterThanOrEqual.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(LessThan lessThan) {
        lessThan.getLhs().accept(this);
        lessThan.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(LessThanOrEqual lessThanOrEqual) {
        lessThanOrEqual.getLhs().accept(this);
        lessThanOrEqual.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd) {
        logicalAnd.getLhs().accept(this);
        logicalAnd.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr) {
        logicalOr.getLhs().accept(this);
        logicalOr.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(Multiplication multiplication) {
        multiplication.getLhs().accept(this);
        multiplication.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(NotEqual notEqual) {
        notEqual.getLhs().accept(this);
        notEqual.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        subtraction.getLhs().accept(this);
        subtraction.getRhs().accept(this);

        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Void visit(Expression expression) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public Void visit(StringerLiteral stringerLiteral) {
        return null;
    }

    @Override
    public Void visit(Form node) {
        node.getQuestions().forEach(item -> item.accept(this));

        return null;
    }

    @Override
    public Void visit(Question node) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion node) {
        question = node;
        node.getComputedValue().accept(this);

        return null;
    }

    @Override
    public Void visit(IfThen node) {
        node.getCondition().accept(this);

        return null;
    }

    @Override
    public Void visit(IfThenElse node) {
        node.getCondition().accept(this);

        return null;
    }

    @Override
    public Void visit(GroupedExpression groupedExpression) {
        groupedExpression.getExpression().accept(this);

        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        if (question != null) {
            addDependency(question.getIdentifier(), identifier.getValue());
            checkForCircularDependencies();
        }

        return null;
    }

    private void addDependency(java.lang.String value, java.lang.String dependency) {
        List<java.lang.String> valueDependencies = initDependencies(value);

        valueDependencies.add(dependency);
        dependencies.put(value, valueDependencies);
    }

    private List<java.lang.String> initDependencies(java.lang.String value) {
        if (dependencies.containsKey(value)) {
            return dependencies.get(value);
        }

        return new ArrayList<>();
    }

    private List<java.lang.String> getDependencies(java.lang.String value) {
        if (dependencies.containsKey(value)) {
            return dependencies.get(value);
        }

        return new ArrayList<>();
    }

    private void checkForCircularDependencies() {
        List<java.lang.String> dependencies = getDependencies(question.getIdentifier());

        for (java.lang.String dependency : dependencies) {
            if (isCircularDependency(getDependencies(dependency))) {
                messages.addError(new CyclicDependency(question.getLineNumber(), question.getIdentifier(), dependency));
            }
        }
    }

    private boolean isCircularDependency(List<java.lang.String> circularDependencies) {
        return circularDependencies.contains(question.getIdentifier()) && !circularDependencies.isEmpty();
    }
}
