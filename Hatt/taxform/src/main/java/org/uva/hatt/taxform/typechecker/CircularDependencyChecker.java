package org.uva.hatt.taxform.typechecker;

import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.ComputedQuestion;
import org.uva.hatt.taxform.ast.nodes.items.IfThen;
import org.uva.hatt.taxform.ast.nodes.items.IfThenElse;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.ast.visitors.ExpressionVisitor;
import org.uva.hatt.taxform.ast.visitors.Visitor;
import org.uva.hatt.taxform.typechecker.messages.Message;
import org.uva.hatt.taxform.typechecker.messages.error.CyclicDependency;

import java.util.*;

public class CircularDependencyChecker implements Visitor, ExpressionVisitor<Set<String>> {

    private final Map<java.lang.String, List<java.lang.String>> dependencies;
    private final Message message;
    private Question question;

    public CircularDependencyChecker(Message message){
        this.message = message;
        dependencies = new HashMap<>();
    }

    @Override
    public Set<String> visit(Addition addition){
        addition.getLhs().accept(this);
        addition.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(Division division){
        division.getLhs().accept(this);
        division.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(Equal equal){
        equal.getLhs().accept(this);
        equal.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(GreaterThan greaterThan){
        greaterThan.getLhs().accept(this);
        greaterThan.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(GreaterThanOrEqual greaterThanOrEqual){
        greaterThanOrEqual.getLhs().accept(this);
        greaterThanOrEqual.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(LessThan lessThan){
        lessThan.getLhs().accept(this);
        lessThan.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(LessThanOrEqual lessThanOrEqual){
        lessThanOrEqual.getLhs().accept(this);
        lessThanOrEqual.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(LogicalAnd logicalAnd){
        logicalAnd.getLhs().accept(this);
        logicalAnd.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(LogicalOr logicalOr){
        logicalOr.getLhs().accept(this);
        logicalOr.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(Multiplication multiplication){
        multiplication.getLhs().accept(this);
        multiplication.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(NotEqual notEqual){
        notEqual.getLhs().accept(this);
        notEqual.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(Subtraction subtraction){
        subtraction.getLhs().accept(this);
        subtraction.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(BooleanLiteral booleanLiteral){
        return null;
    }

    @Override
    public Set<String> visit(Expression expression) {
        return null;
    }

    @Override
    public Set<String> visit(IntegerLiteral integerLiteral){
        return null;
    }

    @Override
    public Set<String> visit(StringerLiteral stringerLiteral){
        return null;
    }

    @Override
    public Set<String> visit(Form node) {
        node.getQuestions().forEach(item -> item.accept(this));

        return null;
    }

    @Override
    public Set<String> visit(Question node) {
        return null;
    }

    @Override
    public Set<String> visit(ComputedQuestion node) {
        question = node;
        node.getComputedValue().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(IfThen node) {
        node.getCondition().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(IfThenElse node) {
        node.getCondition().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(Boolean node) {
        return null;
    }

    @Override
    public Set<String> visit(Integer node) {
        return null;
    }

    @Override
    public Set<String> visit(Money node) {
        return null;
    }

    @Override
    public Set<String> visit(String node) {
        return null;
    }

    @Override
    public Set<String> visit(ValueType node) {
        return null;
    }

    @Override
    public Set<String> visit(BooleanExpression node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(GroupedExpression groupedExpression){
        groupedExpression.getExpression().accept(this);

        return null;
    }

    @Override
    public Set<String> visit(Identifier identifier){
        if (question != null) {
            addDependency(question.getValue(), identifier.getValue());
            checkForCircularDependencies();
        }

        return null;
    }

    private void addDependency(java.lang.String value, java.lang.String dependency){
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

    private List<java.lang.String> getDependencies(java.lang.String value){
        if (dependencies.containsKey(value)) {
            return dependencies.get(value);
        }

        return new ArrayList<>();
    }

    private void checkForCircularDependencies() {
        List<java.lang.String> dependencies = getDependencies(question.getValue());

        for (java.lang.String dependency : dependencies) {
            if (isCircularDependency(getDependencies(dependency))) {
                message.addError(new CyclicDependency(question.getLineNumber(), question.getValue(), dependency));
            }
        }
    }

    private boolean isCircularDependency(List<java.lang.String> circularDependencies) {
        return circularDependencies.contains(question.getValue()) && !circularDependencies.isEmpty();
    }
}
