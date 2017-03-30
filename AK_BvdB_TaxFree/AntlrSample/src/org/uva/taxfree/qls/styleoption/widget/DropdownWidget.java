package org.uva.taxfree.qls.styleoption.widget;

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
    public void applyStyle(JComponent component) {
        
    }
}
