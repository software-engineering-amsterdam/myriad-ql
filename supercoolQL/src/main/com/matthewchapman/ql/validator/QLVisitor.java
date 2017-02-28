package com.matthewchapman.ql.validator;

import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public interface QLVisitor<T> {

    T visit(Question question);
    T visit(IfStatement ifStatement);
    T visit(IfElseStatement ifElseStatement);
    T visit(Parameter parameter);
    T visit(ParameterGroup parameterGroup);

}
