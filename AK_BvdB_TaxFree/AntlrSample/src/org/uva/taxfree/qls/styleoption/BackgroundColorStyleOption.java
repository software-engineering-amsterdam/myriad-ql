package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.gui.widgets.GuiComponent;
import org.uva.taxfree.ql.model.SourceInfo;

import java.awt.*;

public class BackgroundColorStyleOption extends StyleOption {
    private final String mColorValue;

    public BackgroundColorStyleOption(String colorValue, SourceInfo sourceInfo) {
        super(sourceInfo);
        mColorValue = colorValue;
    }

    public void applyStyle(GuiComponent component) {
        component.setBackgroundColor(Color.decode(mColorValue));
    }
}
