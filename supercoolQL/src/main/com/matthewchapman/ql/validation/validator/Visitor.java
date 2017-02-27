package com.matthewchapman.ql.validation.validator;

import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public interface Visitor<T> {

    T visit(Question question);
    T visit(IfStatement ifStatement);

}
