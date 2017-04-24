package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;

import java.awt.*;

public class BackgroundColorStyleOption extends StyleOption {
    private final String mColorValue;

    public BackgroundColorStyleOption(String colorValue, SourceInfo sourceInfo) {
        super(sourceInfo);
        mColorValue = colorValue;
    }

    public void applyStyle(Widget widget) {
        widget.setBackgroundColor(Color.decode(mColorValue));
    }
}
