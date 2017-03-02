package com.matthewchapman.ql.validator;

import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public interface QLVisitor<T> {

    //Statement
    T visit(Question question);

    T visit(IfStatement ifStatement);

    T visit(IfElseStatement ifElseStatement);

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

}
