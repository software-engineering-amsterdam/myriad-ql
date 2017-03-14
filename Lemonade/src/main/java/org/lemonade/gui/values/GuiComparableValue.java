package org.lemonade.gui.values;

/**
 *
 */
public abstract class GuiComparableValue<T> extends GuiValue<T> {

    public abstract GuiBooleanValue gT(final GuiComparableValue<?> that);
    public abstract GuiBooleanValue lT(final GuiComparableValue<?> that);
    public abstract GuiBooleanValue gTEq(final GuiComparableValue<?> that);
    public abstract GuiBooleanValue lTEq(final GuiComparableValue<?> that);

}
