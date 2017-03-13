package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.atomic.BooleanType;
import com.matthewchapman.ql.ast.atomic.IntegerType;
import com.matthewchapman.ql.ast.atomic.StringType;
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
 *
 * Visitor interface for the QL AST, provides methods for visiting all QL types, expressions, statements, etc.
 */
public interface QLVisitor<T> {

    //Statement
    T visit(Question question, String context);

    T visit(IfStatement ifStatement, String context);

    T visit(IfElseStatement ifElseStatement, String context);

    T visit(CalculatedQuestion calculatedQuestion, String context);

    //Expression
    T visit(Parameter parameter, String context);

    T visit(ParameterGroup parameterGroup, String context);

    //Binary
    T visit(Addition addition, String context);

    T visit(Division division, String context);

    T visit(Equal equal, String context);

    T visit(GreaterThan greaterThan, String context);

    T visit(GreaterThanEqualTo greaterThanEqualTo, String context);

    T visit(LessThan lessThan, String context);

    T visit(LessThanEqualTo lessThanEqualTo, String context);

    T visit(LogicalAnd logicalAnd, String context);

    T visit(LogicalOr logicalOr, String context);

    T visit(Multiplication multiplication, String context);

    T visit(NotEqual notEqual, String context);

    T visit(Subtraction subtraction, String context);

    //Unary
    T visit(Negation negation, String context);

    //Types
    T visit(BooleanType booleanType, String context);

    T visit(IntegerType integerType, String context);

    T visit(StringType stringType, String context);

}
