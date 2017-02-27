package ql.ast.values;

import ql.ast.types.Type;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Erik on 21-2-2017.
 */
public abstract class Value<T> {
    abstract T getValue();

    public Value add(Value other) {
        throw new NotImplementedException();
    }

    protected Value addEval(IntValue other) {
        throw new NotImplementedException();
    }

    protected Value addEval(FloatValue other) {
        throw new NotImplementedException();
    }


    public Value mul(Value other) {
        throw new NotImplementedException();
    }

    protected Value mulEval(IntValue other) {
        throw new NotImplementedException();
    }

    protected Value mulEval(FloatValue other) {
        throw new NotImplementedException();
    }


    public Value div(Value other) {
        throw new NotImplementedException();
    }

    protected Value divEval(IntValue other) {
        throw new NotImplementedException();
    }

    protected Value divEval(FloatValue other) {
        throw new NotImplementedException();
    }


    public Value gT(Value other) {
        throw new NotImplementedException();
    }

    protected Value gTEval(IntValue other) {
        throw new NotImplementedException();
    }

    protected Value gTEval(FloatValue other) {
        throw new NotImplementedException();
    }


    public Value and(Value other) {
        throw new NotImplementedException();
    }

    protected Value andEval(BooleanValue other) {
        throw new NotImplementedException();
    }


    public Value or(Value other) {
        throw new NotImplementedException();
    }

    protected Value orEval(BooleanValue other) {
        throw new NotImplementedException();
    }


    public Value gEq(Value other) {
        throw new NotImplementedException();
    }

    public Value lEq(Value other) {
        throw new NotImplementedException();
    }

    public Value lT(Value other) {
        throw new NotImplementedException();
    }

    public Value sub(Value other) {
        throw new NotImplementedException();
    }


    public Value eq(Value other) {
        return new BooleanValue(other.equals(this));
    }

    public Value nEq(Value other) {
        return new BooleanValue(!other.equals(this));
    }

        /* Mono operators */

    public Value neg() {
        throw new NotImplementedException();
    }

    public Value not() {
        throw new NotImplementedException();
    }

    public Value pos() {
        throw new NotImplementedException();
    }

}
