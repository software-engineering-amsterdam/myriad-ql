package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.Type;
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

    @Override
    public void callOnUpdate(FormListener listener) {
        mDropdownBox.addActionListener(unusedEvent -> listener.updateForm());
    }

    @Override
    public boolean supports(Type supportedType) {
        return supportedType.equals(new BooleanType());
    }
}
