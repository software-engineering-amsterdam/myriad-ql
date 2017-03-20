package ql.visistor.interfaces;

import ql.ast.types.*;

/**
 * Created by Erik on 20-3-2017.
 */
public interface TypeVisitor<T> {
    T visit(BooleanType node);

    T visit(IntType node);

    T visit(FloatType node);

    T visit(StringType node);

    T visit(ErrorType node);
    
}
