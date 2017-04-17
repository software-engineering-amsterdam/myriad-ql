package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.Value;

import javax.swing.*;

public class RadioWidget extends WidgetStyleOption {

    private final JRadioButton mButtonFalse;
    private final JRadioButton mButtonTrue;

    public RadioWidget(String labelTrue, String labelFalse, SourceInfo sourceInfo) {
        super(sourceInfo);
        mButtonTrue = new JRadioButton(labelTrue);
        mButtonFalse = new JRadioButton(labelFalse);
        mButtonFalse.setSelected(true);
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(mButtonFalse);
        radioButtons.add(mButtonTrue);
    }

    @Override
    protected JComponent generateComponent() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(mButtonTrue);
        buttonPanel.add(mButtonFalse);
        return buttonPanel;
    }

    @Override
    public Value resolve() {
        return new BooleanValue(mButtonTrue.isSelected());
    }
}
