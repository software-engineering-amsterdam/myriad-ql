package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.Type;

public abstract class StyleOption {

    private SourceInfo mSourceInfo;

    public StyleOption(SourceInfo sourceInfo) {
        mSourceInfo = sourceInfo;
    }

    public abstract void applyStyle(Widget widget);

    public boolean supports(Type supportedType) {
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
