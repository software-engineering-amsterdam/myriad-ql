/**
 * Value.java.
 */

package ql.gui.formenvironment.values;

public abstract class Value {

    public abstract Object getValue();

    public boolean undefined() {
        return false;
    }

    public Value addition(Value value) {
        return new UndefinedValue();
    }

    public Value subtraction(Value value) {
        return new UndefinedValue();
    }

    public Value and(Value value) {
        return new UndefinedValue();
    }

    public Value or(Value value) {
        return new UndefinedValue();
    }

    public Value negation() {
        return new UndefinedValue();
    }

    public Value eq(Value value) {
        return new UndefinedValue();
    }

    public Value neq(Value value) {
        return new UndefinedValue();
    }

    public Value gt(Value value) {
        return new UndefinedValue();
    }

    public Value gteq(Value value) {
        return new UndefinedValue();
    }

    public Value lt(Value value) {
        return new UndefinedValue();
    }

    public Value lteq(Value value) {
        return new UndefinedValue();
    }

    public Value division(Value value) {
        return new UndefinedValue();
    }

    public Value multiplication(Value value) {
        return new UndefinedValue();
    }

    public Value negative() {
        return new UndefinedValue();
    }

    public Value positive() {
        return new UndefinedValue();
    }
}
