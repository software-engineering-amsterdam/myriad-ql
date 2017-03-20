package org.uva.hatt.taxform.ast.visitors;


import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.error.DuplicateDeclaration;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.ExceptionHandler;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.error.UndefinedReference;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.warning.DuplicateLabel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TypeChecker implements Visitor{

    private ExceptionHandler exceptionHandler;
    private List<java.lang.String> questions = new LinkedList<>();
    private Map<java.lang.String, ValueType> declarations = new HashMap<>();

    public TypeChecker(ExceptionHandler exceptionHandler)
    {
        this.exceptionHandler = exceptionHandler;
    }
    @Override
    public Form visit(Form node){
        for (Item item : node.getQuestions()) {
            item.accept(this);
        }
        return null;
    }

    @Override
    public Question visit(Question node){
        if (declarations.containsKey(node.getValue())) {
            exceptionHandler.addError(new DuplicateDeclaration(node.getLineNumber(), node.getValue()));
        }

        if (questions.contains(node.getQuestion().toLowerCase())) {
            exceptionHandler.addWarning(new DuplicateLabel(node.getLineNumber(), node.getQuestion()));
        }

        declarations.put(node.getValue(), node.getType());
        questions.add(node.getQuestion().toLowerCase());
        return null;
    }

    @Override
    public ComputedQuestion visit(ComputedQuestion computedQuestion) {
        return null;
    }

    @Override
    public IfThen visit(IfThen node) {
        node.getCondition().accept(this);

        node.getThenStatements().forEach(item -> item.accept(this));
        return null;
    }

    @Override
    public IfThenElse visit(IfThenElse node){
        node.getCondition().accept(this);

        node.getThenStatements().forEach(item -> item.accept(this));
        node.getElseStatements().forEach(item -> item.accept(this));
        return null;
    }

    @Override
    public Boolean visit(Boolean node){
        return null;
    }

    @Override
    public Integer visit(Integer node){
        return null;
    }

    @Override
    public Money visit(Money node){
        return null;
    }

    @Override
    public String visit(String node){
        return null;
    }

    @Override
    public ValueType visit(ValueType node){
        return null;
    }

    @Override
    public BooleanExpression visit(BooleanExpression node) {
        return null;
    }

    @Override
    public GroupedExpression visit(GroupedExpression node) {
        return null;
    }

    @Override
    public ValueType visit(Identifier identifier) {
        if (declarations.keySet().contains(identifier.getId())) {
            return declarations.get(identifier.getId());
        }

        exceptionHandler.addError(new UndefinedReference(identifier.getLineNumber(), identifier.getId()));
        return null;
    }

    @Override
    public StringerLiteral visit(StringerLiteral stringerLiteral) {
        return null;
    }

    @Override
    public IntegerLiteral visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public BooleanLiteral visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Expression visit(Expression expression) { return null; }
}
