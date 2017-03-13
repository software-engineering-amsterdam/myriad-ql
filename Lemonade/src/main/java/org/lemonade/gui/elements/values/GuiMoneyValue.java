package org.lemonade.gui.elements.values;

public class GuiMoneyValue extends GuiValue<Double> {

    private Double value;

    public GuiMoneyValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void update(Double value) {
        this.value = value;
    }

}
