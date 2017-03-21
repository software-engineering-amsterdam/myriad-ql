package com.matthewchapman.ql.visitors;

import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.ErrorType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;

/**
 * Created by matt on 18/03/2017.
 */
public interface TypeVisitor<T, C> {

    //Types
    T visit(BooleanType booleanType, C context);

    T visit(IntegerType integerType, C context);

    T visit(StringType stringType, C context);

    T visit(ErrorType errorType, C context);

}
