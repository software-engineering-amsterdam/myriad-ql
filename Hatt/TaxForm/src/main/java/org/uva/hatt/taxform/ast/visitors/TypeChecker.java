package org.uva.hatt.taxform.ast.visitors;


import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.binary.*;
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
//import org.uva.hatt.taxform.ast.visitors.exceptionHandler.error.TypeMismatch;
//import org.uva.hatt.taxform.ast.visitors.exceptionHandler.error.UndefinedReference;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.warning.DuplicateLabel;

import java.util.*;


public class TypeChecker implements Visitor{

    private ExceptionHandler exceptionHandler;
    private List<java.lang.String> questions = new LinkedList<>();
    private Map<java.lang.String, ValueType> declarations = new HashMap<>();
    private Set<java.lang.String> dependencies = new HashSet<>();

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
        Expression expression = node.getCondition();
        Object type = expression.accept(this);

        node.getCondition().accept(this);

        node.getThenStatements().forEach(item -> item.accept(this));
        return null;
    }

    @Override
    public IfThenElse visit(IfThenElse node){
        Expression expression = node.getCondition();
        ValueType type = (ValueType) expression.accept(this);

        if(!type.isBoolean())
        {
//            exceptionHandler.addError(new TypeMismatch(node.getLineNumber(), type.name()));
        }

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
//        if (declarations.keySet().contains(identifier.getId())) {
//            return declarations.get(identifier.getId());
//        }
//
//        exceptionHandler.addError(new UndefinedReference(identifier.getLineNumber(), identifier.getId()));
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

    @Override
    public ValueType visit(Addition addition){

        return new Integer(1);
        //return visitBinaryExpression(addition);
    }

    @Override
    public ValueType visit(Division division){
        return new Boolean(1);
        //return visitBinaryExpression(division);
    }

    @Override
    public ValueType visit(Equal equal){
        return new Boolean(1);
        //return visitBinaryExpression(equal);
    }

    @Override
    public ValueType visit(GreaterThan greaterThan){
        return new Boolean(1);
        //return visitBinaryExpression(greaterThan);
    }

    @Override
    public ValueType visit(GreaterThanOrEqual greaterThanOrEqual){
        return new Boolean(1);
        //return visitBinaryExpression(greaterThanOrEqual);
    }

    @Override
    public ValueType visit(LessThan lessThan){
        return new Boolean(1);
        //return visitBinaryExpression(lessThan);
    }

    @Override
    public ValueType visit(LessThanOrEqual lessThanOrEqual){
        return new Boolean(1);
        //return visitBinaryExpression(lessThanOrEqual);
    }

    @Override
    public LogicalAnd visit(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public LogicalOr visit(LogicalOr logicalOr) {
        return null;
    }

//    @Override
//    public Set<java.lang.String> visit(LogicalAnd logicalAnd){
//
//    }

//    @Override
//    public Set<java.lang.String> visit(LogicalOr){
//
//    }

    @Override
    public ValueType visit(Multiplication multiplication){
        return new Boolean(1);
        //return visitBinaryExpression(multiplication);
    }

    @Override
    public ValueType visit(NotEqual notEqual){
        return new Boolean(1);
        //return visitBinaryExpression(notEqual);
    }

    @Override
    public ValueType visit(Subtraction subtraction){
        return new Boolean(1);
        //return visitBinaryExpression(subtraction);
    }

    private ValueType visitBinaryExpression(BooleanExpression expression){
        ValueType lhsType = (ValueType)expression.getLhs().accept(this);
        ValueType rhsType = (ValueType) expression.getRhs().accept(this);
        return lhsType;
    }
}
