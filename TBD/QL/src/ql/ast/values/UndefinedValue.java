package ql.ast.values;

import ql.ast.types.ErrorType;
import ql.ast.types.FloatType;

/**
 * Created by Erik on 21-2-2017.
 */
public class UndefinedValue extends Value<String> {

    @Override
    String getValue() {
        return "Undefined";
    }

    @Override
    public String toString() {
        return "Undefined";
    }

    @Override
    public Value add(Value other) {
        return this;
    }

    @Override
    public Value mul(Value other) {
        return this;
    }

    @Override
    public Value div(Value other) {
        return this;
    }

    @Override
    public Value gT(Value other) {
        return this;
    }

    @Override
    public Value and(Value other) {
        return this;
    }

    @Override
    public Value or(Value other) {
        return this;
    }

    @Override
    public Value gEq(Value other) {
        return this;
    }

    @Override
    public Value lEq(Value other) {
        return this;
    }

    @Override
    public Value lT(Value other) {
        return this;
    }

    @Override
    public Value sub(Value other) {
        return this;
    }

    @Override
    public Value eq(Value other) {
        return this;
    }

    @Override
    public Value nEq(Value other) {
        return this;
    }

    @Override
    public Value neg() {
        return this;
    }

    @Override
    public Value not() {
        return this;
    }

    @Override
    public Value pos() {
        return this;
    }
}
