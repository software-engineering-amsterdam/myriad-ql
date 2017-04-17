package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.Value;

import javax.swing.*;

public class CheckboxWidget extends WidgetStyleOption {

    private final JCheckBox mCheckBox;

    public CheckboxWidget(SourceInfo sourceInfo) {
        super(sourceInfo);
        mCheckBox = new JCheckBox();

    }

    @Override
    protected JComponent generateComponent() {
        return mCheckBox;
    }

    @Override
    public Value resolve() {
        return new BooleanValue(mCheckBox.isSelected());
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mCheckBox.addActionListener(unusedEvent -> listener.updateForm());
    }
}
