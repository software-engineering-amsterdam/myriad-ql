package org.uva.hatt.taxform.ast.visitors;


import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.FormId;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.ComputationExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.items.Conditional;
import org.uva.hatt.taxform.ast.nodes.items.Item;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TypeChecker implements Visitor{

    private Map<java.lang.String, ValueType> declarations;
    private List<java.lang.String> questions;

    public TypeChecker()
    {
        declarations = new HashMap<>();
        this.questions = new LinkedList<>();
    }
    @Override
    public Form visit(Form node){
        for (Item item : node.getQuestions()) {
            item.accept(this);
        }
        return null;
    }

    @Override
    public FormId visit(FormId node){
        node.accept(this);
        return null;
    }

    @Override
    public Question visit(Question node){
        if (declarations.containsKey(node.getValue())) {
            // to do: error -> Duplicate declaration
        }

        if (questions.contains(node.getQuestion().toLowerCase())) {
            // to do: warning -> duplicate question label
        }

        declarations.put(node.getValue(), node.getType());
        questions.add(node.getQuestion().toLowerCase());

        return null;
    }

    @Override
    public Conditional visit(Conditional node){

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
    public ComputationExpression visit(ComputationExpression node) {
        return null;
    }

    @Override
    public GroupedExpression visit(GroupedExpression node) {
        return null;
    }

    @Override
    public Identifier visit(Identifier identifier) {
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
    public Expression visit(Expression expression) {
        return null;
    }
}
