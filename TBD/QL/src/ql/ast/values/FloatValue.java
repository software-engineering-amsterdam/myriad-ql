package ql.ast.values;

import ql.ast.types.BooleanType;
import ql.ast.types.FloatType;

/**
 * Created by Erik on 21-2-2017.
 */
public class FloatValue extends Value<Float>{
    private final float value;

    public FloatValue(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Value add(Value other) {
        return other.addEval(this);
    }

    @Override
    protected Value addEval(FloatValue other) {
        return new FloatValue(this.getValue() + other.getValue());
    }

    @Override
    protected Value addEval(IntValue other) {
        return new FloatValue(this.getValue() + other.getValue());
    }

    @Override
    public Value div(Value other) {
        return other.divEval(this);
    }

    @Override
    protected Value divEval(FloatValue other) {
        if(this.getValue() == 0f){
            return new UndefinedValue();
        }
        return new FloatValue(other.getValue() / this.getValue());
    }

    @Override
    protected Value divEval(IntValue other) {
        if(this.getValue() == 0f){
            return new UndefinedValue();
        }
        return new FloatValue(other.getValue() / this.getValue());
    }

    @Override
    public Value mul(Value other) {
        return other.mulEval(this);
    }

    @Override
    protected Value mulEval(FloatValue other) {
        return new FloatValue(this.getValue() * other.getValue());
    }

    @Override
    protected Value mulEval(IntValue other) {
        return new FloatValue(this.getValue() * other.getValue());
    }

    @Override
    public Value gEq(Value other) {
        return (new BooleanValue(other.equals(this))).or(gT(other));
    }

    @Override
    public Value gT(Value other) {
        return lEq(other).not();
    }

    @Override
    protected Value gTEval(FloatValue other) {
        return new BooleanValue(this.getValue() > other.getValue());
    }

    @Override
    protected Value gTEval(IntValue other) {
        return new BooleanValue(this.getValue() > other.getValue());
    }

    @Override
    public Value lEq(Value other) {
        return (new BooleanValue(other.equals(this))).or(lT(other));
    }

    @Override
    public Value lT(Value other) {
        return other.gTEval(this);
    }

    @Override
    public Value nEq(Value other) {
        return new BooleanValue(!other.equals(this));
    }

    @Override
    public Value sub(Value other) {
        return other.neg().add(this);
    }

    @Override
    public Value neg() {
        return new FloatValue(-getValue());
    }

    @Override
    public Value pos() {
        return new FloatValue(-getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloatValue that = (FloatValue) o;

        return Float.compare(that.value, value) == 0;
    }
}
