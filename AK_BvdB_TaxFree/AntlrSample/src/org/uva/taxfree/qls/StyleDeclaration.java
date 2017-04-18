package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.StyleOption;

import java.util.List;

public class StyleDeclaration {

    private final List<StyleOption> mStyleOptions;
    private SourceInfo mSourceInfo;

    public StyleDeclaration(List<StyleOption> styleOptions, SourceInfo sourceInfo) {
        mStyleOptions = styleOptions;
        mSourceInfo = sourceInfo;
    }

    public void applyStyle(Widget widget) {
        for (StyleOption styleOption : mStyleOptions) {
            styleOption.applyStyle(widget);
        }
    }

    public String sourceInfo() {
        return mSourceInfo.sourceString();
    }


    protected boolean supports(Type supportedType) {
        for (StyleOption styleOption : mStyleOptions) {
            if (!styleOption.supports(supportedType)) {
                return false;
            }
        }
        return true;
    }
}
