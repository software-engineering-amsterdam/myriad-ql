package org.uva.taxfree.qls;

import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.StyleOption;

import javax.swing.*;
import java.util.List;

public class StyleDeclaration {
    private final List<StyleOption> mStyleOptions;
    private final Type mType;

    public StyleDeclaration(Type type, List<StyleOption> styleOptions) {
        mStyleOptions = styleOptions;
        mType = type;
    }

    public void applyStyle(JComponent component) {
        for (StyleOption styleOption : mStyleOptions) {
            styleOption.applyStyle(component);
        }
    }
}
