package com.matthewchapman.ql.validation.visitor;

import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Visitor interface for the QL AST, provides methods for visiting all QL types, expressions, statements, etc.
 */
public interface QLVisitor<T, C> {

    //Statement
    T visit(Question question, C context);

    T visit(IfStatement ifStatement, C context);

    T visit(IfElseStatement ifElseStatement, C context);

    T visit(CalculatedQuestion calculatedQuestion, C context);

    //Expression
    T visit(Parameter parameter, C context);

    T visit(ParameterGroup parameterGroup, C context);

    T visit(StringLiteral stringLiteral, C context);

    T visit(IntegerLiteral integerLiteral, C context);

    T visit(BooleanLiteral booleanLiteral, C context);

    //Binary
    T visit(Addition addition, C context);

    T visit(Division division, C context);

    T visit(Equal equal, C context);

    T visit(GreaterThan greaterThan, C context);

    T visit(GreaterThanEqualTo greaterThanEqualTo, C context);

    T visit(LessThan lessThan, C context);

    T visit(LessThanEqualTo lessThanEqualTo, C context);

    T visit(LogicalAnd logicalAnd, C context);

    T visit(LogicalOr logicalOr, C context);

    T visit(Multiplication multiplication, C context);

    T visit(NotEqual notEqual, C context);

    T visit(Subtraction subtraction, C context);

    //Unary
    T visit(Negation negation, C context);

    //Types
    T visit(BooleanType booleanType, C context);

    T visit(IntegerType integerType, C context);

    T visit(StringType stringType, C context);

}
