package com.matthewchapman.ql.visitors;

import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.ast.expression.unary.Negation;

/**
 * Created by matt on 18/03/2017.
 */
public interface ExpressionVisitor<T, C> {

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

    //Parameters
    T visit(Parameter parameter, C context);

    T visit(ParameterGroup parameterGroup, C context);

    T visit(StringLiteral stringLiteral, C context);

    T visit(IntegerLiteral integerLiteral, C context);

    T visit(BooleanLiteral booleanLiteral, C context);
}
