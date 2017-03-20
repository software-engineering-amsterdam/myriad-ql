package ql.ast.values;

/**
 * Created by Erik on 21-2-2017.
 */
public class UndefinedValue extends Value<String> {

    @Override
    public String getValue() {
        return "";
    }

    public Value add(Value other) {
        return this;
    }

    protected Value addEval(IntValue other) {
        return this;
    }

    protected Value addEval(FloatValue other) {
        return this;
    }


    public Value mul(Value other) {
        return this;
    }

    protected Value mulEval(IntValue other) {
        return this;
    }

    protected Value mulEval(FloatValue other) {
        return this;
    }


    public Value div(Value other) {
        return this;
    }

    protected Value divEval(IntValue other) {
        return this;
    }

    protected Value divEval(FloatValue other) {
        return this;
    }


    public Value gT(Value other) {
        return this;
    }

    protected Value gTEval(IntValue other) {
        return this;
    }

    protected Value gTEval(FloatValue other) {
        return this;
    }


    public Value and(Value other) {
        return this;
    }

    protected Value andEval(BooleanValue other) {
        return this;
    }


    public Value or(Value other) {
        return this;
    }

    protected Value orEval(BooleanValue other) {
        return this;
    }


    public Value gEq(Value other) {
        return this;
    }

    public Value lEq(Value other) {
        return this;
    }

    public Value lT(Value other) {
        return this;
    }

    public Value sub(Value other) {
        return this;
    }


    public Value eq(Value other) {
        return new BooleanValue(other.equals(this));
    }

    public Value nEq(Value other) {
        return new BooleanValue(!other.equals(this));
    }

        /* Mono operators */

    public Value neg() {
        return this;
    }

    public Value not() {
        return this;
    }

    public Value pos() {
        return this;
    }
}
