package org.lemonade.gui.values;

import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;

/**
 *
 */
public abstract class GuiNumericalValue<T> extends GuiComparableValue<T> {

    public abstract GuiNumericalValue<?> plus(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> plus(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> plus(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> minus(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> minus(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> minus(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> product(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> product(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> product(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> divide(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> divide(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> divide(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> neg();

    public GuiNumericalValue<?> plus(final GuiNumericalValue<?> that) {
        if (that.getType().isOf(new QLIntegerType().getClass())) {
            return this.plus((GuiIntegerValue) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.plus((GuiDecimalValue) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.plus((GuiMoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GuiNumericalValue<?> product(final GuiNumericalValue<?> that) {
        if (that.getType().isOf(new QLIntegerType().getClass())) {
            return this.product((GuiIntegerValue) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.product((GuiDecimalValue) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.product((GuiMoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GuiNumericalValue<?> minus(final GuiNumericalValue<?> that) {
        if (that.getType().isOf(QLIntegerType.class)) {
            return this.minus((GuiIntegerValue) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.minus((GuiDecimalValue) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.minus((GuiMoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GuiNumericalValue<?> divide(final GuiNumericalValue<?> that) {
        if (that.getType().isOf(new QLIntegerType().getClass())) {
            return this.divide((GuiIntegerValue) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.divide((GuiDecimalValue) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.divide((GuiMoneyValue) that);

        } else {
            throw new IllegalArgumentException();
        }
    }
}
