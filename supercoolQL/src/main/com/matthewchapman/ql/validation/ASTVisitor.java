package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.atomic.BooleanLiteral;
import com.matthewchapman.ql.ast.atomic.IntegerLiteral;
import com.matthewchapman.ql.ast.atomic.StringLiteral;
import com.matthewchapman.ql.ast.expression.CalculatedValue;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public class ASTVisitor<ASTNode> implements Visitor<ASTNode> {

    @Override
    public ASTNode visitBooleanLiteral(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public ASTNode visitIntegerLiteral(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public ASTNode visitStringLiteral(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public ASTNode visitCalculatedValue(CalculatedValue calculatedValue) {
        return null;
    }

    @Override
    public ASTNode visitParameter(Parameter parameter) {
        return null;
    }

    @Override
    public ASTNode visitParameterGroup(ParameterGroup parameterGroup) {
        return null;
    }

    @Override
    public ASTNode visitAddition(Addition addition) {
        return null;
    }

    @Override
    public ASTNode visitDivision(Division division) {
        return null;
    }

    @Override
    public ASTNode visitEqual(Equal equal) {
        return null;
    }

    @Override
    public ASTNode visitGreaterThan(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public ASTNode visitGreaterThanEqualTo(GreaterThanEqualTo greaterThanEqualTo) {
        return null;
    }

    @Override
    public ASTNode visitLessThan(LessThan lessThan) {
        return null;
    }

    @Override
    public ASTNode visitLessThanEqualTo(LessThanEqualTo lessThanEqualTo) {
        return null;
    }

    @Override
    public ASTNode visitLogicalAnd(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public ASTNode visitLogicalOr(LogicalOr logicalOr) {
        return null;
    }

    @Override
    public ASTNode visitMultiplication(Multiplication multiplication) {
        return null;
    }

    @Override
    public ASTNode visitNotEqual(NotEqual notEqual) {
        return null;
    }

    @Override
    public ASTNode visitSubtraction(Subtraction subtraction) {
        return null;
    }

    @Override
    public ASTNode visitNegation(Negation negation) {
        return null;
    }

    @Override
    public ASTNode visitQuestion(Question question) {
        return null;
    }

    @Override
    public ASTNode visitIfStatement(IfStatement ifStatement) {
        return null;
    }

    @Override
    public ASTNode visitForm(Form form) {
        System.out.println("Form: " + form.getName());
        
        return null;
    }
}
