package org.uva.taxfree.model.node.widgets;

import javax.swing.*;
import java.awt.*;

public class CalculationWidget extends Widget {
    private final JTextField mTextField;

    public CalculationWidget(String label) {
        super(label);
        mTextField = new JTextField();
        mTextField.setEditable(false);
        mTextField.setPreferredSize(new Dimension(100, 25));
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
    }

    @Override
    public void setVisible(boolean isVisible) {
        //mTextField.setText(resolveValue());
        super.setVisible(isVisible);
    }

    @Override
    public String resolveValue() {
        throw new RuntimeException("Not implemented");
    }
}
