package org.lemonade.gui.values;

import org.lemonade.exceptions.NotSupportedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 */
public abstract class GuiNumericalValue<T> extends GuiComparableValue<T> {

    public abstract GuiNumericalValue<?> plus(GuiNumericalValue<?> that);

    public abstract GuiNumericalValue<?> minus(GuiNumericalValue<?> that);

    public abstract GuiNumericalValue<?> product(GuiNumericalValue<?> that);

    public abstract GuiNumericalValue<?> divide(GuiNumericalValue<?> that);

    public abstract GuiNumericalValue<?> add(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> add(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> add(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> min(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> min(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> min(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> prod(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> prod(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> prod(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> div(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> div(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> div(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> neg();

}
