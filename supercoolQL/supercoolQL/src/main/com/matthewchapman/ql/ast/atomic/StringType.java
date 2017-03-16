package com.matthewchapman.ql.ast.atomic;

/**
 * Created by matt on 08/03/2017.
 */
public class StringType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return type.toString().equals("string");
    }

    @Override
    public String toString() {
        return "string";
    }
}
