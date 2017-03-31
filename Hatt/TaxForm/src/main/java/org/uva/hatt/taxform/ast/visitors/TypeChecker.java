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
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.error.InvalidOperandsTypeToOperator;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.error.TypeMismatch;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.error.UndefinedReference;
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
        validateIfCondition(expression, node.getLineNumber());

        node.getCondition().accept(this);

        node.getThenStatements().forEach(item -> item.accept(this));
        return null;
    }

    @Override
    public IfThenElse visit(IfThenElse node){
        Expression expression = node.getCondition();
        validateIfCondition(expression, node.getLineNumber());

        node.getCondition().accept(this);

        node.getThenStatements().forEach(item -> item.accept(this));
        node.getElseStatements().forEach(item -> item.accept(this));
        return null;
    }

    private void validateIfCondition(Expression expression, int line)
    {
        ValueType type = (ValueType) expression.accept(this);

        if(!type.isBoolean())
        {
            exceptionHandler.addError(new TypeMismatch(line, type.name()));
        }
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
        if (declarations.keySet().contains(identifier.getValue())) {
            return declarations.get(identifier.getValue());
        }

        exceptionHandler.addError(new UndefinedReference(identifier.getLineNumber(), identifier.getValue()));
        return new Unknown();
    }

    @Override
    public ValueType visit(StringerLiteral stringerLiteral) {
        return new String(0);
    }

    @Override
    public ValueType visit(IntegerLiteral integerLiteral) {
        return new Integer(0);
    }

    @Override
    public ValueType visit(BooleanLiteral booleanLiteral) {
        return new Boolean(0);
    }

    @Override
    public Expression visit(Expression expression) { return null; }

    @Override
    public ValueType visit(Addition addition){
        return validateNumericType(addition, "Addition");
    }

    @Override
    public ValueType visit(Division division){
        return validateNumericType(division, "Division");
    }

    @Override
    public ValueType visit(Equal equal){
        ValueType expectedType = new String(0);

        if (!validateBinaryExpression(equal, expectedType).equals(new Unknown())) {
            return new Boolean(0);
        }

        ValueType type = validateNumericType(equal, "Equal");

        if (type.equals(new Unknown()))
        {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(GreaterThan greaterThan){
        ValueType type = validateNumericType(greaterThan, "Greater than");

        if (type.equals(new Unknown()))
        {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(GreaterThanOrEqual greaterThanOrEqual){
        ValueType type = validateNumericType(greaterThanOrEqual, "Greater than or equal");

        if (type.equals(new Unknown()))
        {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(LessThan lessThan){
        ValueType type = validateNumericType(lessThan, "Less than");

        if (type.equals(new Unknown()))
        {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(LessThanOrEqual lessThanOrEqual){
        ValueType type = validateNumericType(lessThanOrEqual, "Less than or equal");

        if (type.equals(new Unknown()))
        {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(LogicalAnd logicalAnd) {
        ValueType expectedType = new Boolean(0);

        if (!validateBinaryExpression(logicalAnd, expectedType).equals(new Unknown())) {
            return expectedType;
        }

        exceptionHandler.addError(new InvalidOperandsTypeToOperator(logicalAnd.getLineNumber(), "Logical AND"));
        return new Unknown();
    }

    @Override
    public ValueType visit(LogicalOr logicalOr) {
        ValueType expectedType = new Boolean(0);

        if (!validateBinaryExpression(logicalOr, expectedType).equals(new Unknown())) {
            return expectedType;
        }

        exceptionHandler.addError(new InvalidOperandsTypeToOperator(logicalOr.getLineNumber(), "Logical OR"));
        return new Unknown();
    }

    @Override
    public ValueType visit(Multiplication multiplication){
        return validateNumericType(multiplication, "Multiplication");
    }

    @Override
    public ValueType visit(NotEqual notEqual){
        ValueType expectedType = new String(0);

        if (!validateBinaryExpression(notEqual, expectedType).equals(new Unknown())) {
            return new Boolean(0);
        }

        ValueType type = validateNumericType(notEqual, "Not equal");

        if (type.equals(new Unknown()))
        {
            return type;
        }
        return new Boolean(0);
    }

    @Override
    public ValueType visit(Subtraction subtraction){
        return validateNumericType(subtraction, "Subtraction");
    }

    private ValueType validateNumericType(BooleanExpression expression, java.lang.String operationName){

        if (!validateBinaryExpression(expression, new Integer(0)).equals(new Unknown())) {
            return new Integer(0);
        }
        if (!validateBinaryExpression(expression, new Money(0)).equals(new Unknown())) {
            return new Money(0);
        }

        exceptionHandler.addError(new InvalidOperandsTypeToOperator(expression.getLineNumber(), operationName));

        return new Unknown();
    }

    private ValueType validateBinaryExpression(BooleanExpression expression, ValueType expectedType){
        ValueType leftType = (ValueType) expression.getLhs().accept(this);
        ValueType rightType = (ValueType) expression.getRhs().accept(this);

        if (leftType.name().equals(rightType.name())) {
            if (leftType.name().equals(expectedType.name())) {
                return leftType;
            }
        }

        return new Unknown();
    }
}
