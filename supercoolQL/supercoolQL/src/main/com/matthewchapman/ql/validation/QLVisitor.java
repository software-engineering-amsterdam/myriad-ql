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
    T visit(Question question);

    T visit(IfStatement ifStatement);

    T visit(IfElseStatement ifElseStatement);

    T visit(CalculatedQuestion calculatedQuestion);

    //Expression
    T visit(Parameter parameter);

    T visit(ParameterGroup parameterGroup);

    //Binary
    T visit(Addition addition);

    T visit(Division division);

    T visit(Equal equal);

    T visit(GreaterThan greaterThan);

    T visit(GreaterThanEqualTo greaterThanEqualTo);

    T visit(LessThan lessThan);

    T visit(LessThanEqualTo lessThanEqualTo);

    T visit(LogicalAnd logicalAnd);

    T visit(LogicalOr logicalOr);

    T visit(Multiplication multiplication);

    T visit(NotEqual notEqual);

    T visit(Subtraction subtraction);

    //Unary
    T visit(Negation negation);

    //Types
    T visit(BooleanType booleanType);

    T visit(IntegerType integerType);

    T visit(StringType stringType);

}
