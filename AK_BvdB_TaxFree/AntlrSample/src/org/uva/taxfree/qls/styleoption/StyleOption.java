package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.gui.widgets.GuiComponent;
import org.uva.taxfree.ql.model.SourceInfo;

public abstract class StyleOption {

    private SourceInfo mSourceInfo;

    public StyleOption(SourceInfo sourceInfo) {
        mSourceInfo = sourceInfo;
    }

    public abstract void applyStyle(GuiComponent component);
}
