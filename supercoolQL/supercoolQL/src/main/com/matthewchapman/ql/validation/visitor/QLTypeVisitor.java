package com.matthewchapman.ql.validation.visitor;

import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.ast.atomic.type.StringType;

/**
 * Created by matt on 18/03/2017.
 */
public interface QLTypeVisitor<T, C> {

    //Types
    T visit(BooleanType booleanType, C context);

    T visit(IntegerType integerType, C context);

    T visit(StringType stringType, C context);

}
