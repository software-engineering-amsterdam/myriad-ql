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
public interface Visitor<T> {

    //literals
    T visitBooleanLiteral (BooleanLiteral booleanLiteral);
    T visitIntegerLiteral (IntegerLiteral integerLiteral);
    T visitStringLiteral (StringLiteral stringLiteral);

    //base expressions
    T visitCalculatedValue (CalculatedValue calculatedValue);
    T visitParameter (Parameter parameter);
    T visitParameterGroup (ParameterGroup parameterGroup);

    //binary expressions
    T visitAddition (Addition addition);
    T visitDivision (Division division);
    T visitEqual (Equal equal);
    T visitGreaterThan (GreaterThan greaterThan);
    T visitGreaterThanEqualTo (GreaterThanEqualTo greaterThanEqualTo);
    T visitLessThan (LessThan lessThan);
    T visitLessThanEqualTo (LessThanEqualTo lessThanEqualTo);
    T visitLogicalAnd (LogicalAnd logicalAnd);
    T visitLogicalOr (LogicalOr logicalOr);
    T visitMultiplication (Multiplication multiplication);
    T visitNotEqual (NotEqual notEqual);
    T visitSubtraction (Subtraction subtraction);

    //unary expressions
    T visitNegation (Negation negation);

    //statements
    T visitQuestion (Question question);
    T visitIfStatement (IfStatement ifStatement);

    //form
    T visitForm (Form form);

}
