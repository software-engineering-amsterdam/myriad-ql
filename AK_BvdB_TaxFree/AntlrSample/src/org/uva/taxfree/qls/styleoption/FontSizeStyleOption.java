package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.gui.widgets.GuiComponent;
import org.uva.taxfree.ql.model.SourceInfo;

import java.awt.*;

public class FontSizeStyleOption extends StyleOption {
    private final int mFontSize;

    public FontSizeStyleOption(int fontSize, SourceInfo sourceInfo) {
        super(sourceInfo);
        mFontSize = fontSize;
    }

    public void applyStyle(GuiComponent component) {
        component.setFontSize(mFontSize);
    }
}
