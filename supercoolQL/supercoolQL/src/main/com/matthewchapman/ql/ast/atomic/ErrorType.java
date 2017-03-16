package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 13/03/2017.
 */
public class ErrorType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return false;
    }

    @Override
    public String toString() {
        return "ERRORTYPE";
    }

}
