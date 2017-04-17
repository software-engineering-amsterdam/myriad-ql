package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.widgets.BooleanWidget;
import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;

import javax.swing.*;

public class DropdownWidget extends WidgetStyleOption {

    private String mLabelTrue;
    private String mLabelFalse;

    public DropdownWidget(String labelTrue, String labelFalse, SourceInfo sourceInfo) {
        super(sourceInfo);
        mLabelTrue = labelTrue;
        mLabelFalse = labelFalse;
    }

    @Override
    public void applyStyle(Widget widget) {
        // Handle the resolveValue callback
    }

    public JComponent createDropdownBox() {
        String[] choices = {mLabelFalse, mLabelTrue};
        return new JComboBox(choices);
    }
}
