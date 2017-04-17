package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.widgets.GuiComponent;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.qls.styleoption.StyleOption;

import java.util.List;

public class StyleDeclaration {

    private final List<StyleOption> mStyleOptions;
    private SourceInfo mSourceInfo;

    public StyleDeclaration(List<StyleOption> styleOptions, SourceInfo sourceInfo) {
        mStyleOptions = styleOptions;
        mSourceInfo = sourceInfo;
    }

    public void applyStyle(GuiComponent component) {
        for (StyleOption styleOption : mStyleOptions) {
            styleOption.applyStyle(component);
        }
    }

    public String sourceInfo() {
        return mSourceInfo.sourceString();
    }
}
