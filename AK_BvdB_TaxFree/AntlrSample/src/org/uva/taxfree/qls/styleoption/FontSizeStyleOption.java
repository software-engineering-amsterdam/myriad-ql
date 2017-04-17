package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;

public class FontSizeStyleOption extends StyleOption {
    private final int mFontSize;

    public FontSizeStyleOption(int fontSize, SourceInfo sourceInfo) {
        super(sourceInfo);
        mFontSize = fontSize;
    }

    public void applyStyle(Widget widget) {
        widget.setFontSize(mFontSize);
    }
}
