package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.widgets.Resolvable;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.Value;

import javax.swing.*;

public class DropdownWidget extends WidgetStyleOption {

    private final JComboBox mDropdownBox;

    public DropdownWidget(String labelTrue, String labelFalse, SourceInfo sourceInfo) {
        super(sourceInfo);
        String[] choices = {labelTrue, labelFalse};
        mDropdownBox = new JComboBox(choices);
    }

    @Override
    protected JComponent generateComponent() {
        return mDropdownBox;
    }

    @Override
    public Value resolve() {
        return new BooleanValue(mDropdownBox.getSelectedIndex() == 0);
    }
}
