package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;

public class RadioWidget extends WidgetStyleOption {

    private String mLabelTrue;
    private String mLabelFalse;

    public RadioWidget(String labelTrue, String labelFalse, SourceInfo sourceInfo) {
        super(sourceInfo);
        mLabelTrue = labelTrue;
        mLabelFalse = labelFalse;
    }

    @Override
    public void applyStyle(Widget widget) {
        
    }
}
