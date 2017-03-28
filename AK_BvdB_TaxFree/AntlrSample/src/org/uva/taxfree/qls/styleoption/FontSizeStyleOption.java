package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.model.SourceInfo;

import javax.swing.*;
import java.awt.*;

public class FontSizeStyleOption extends StyleOption {
    private final int mFontSize;

    public FontSizeStyleOption(int fontSize, SourceInfo sourceInfo) {
        super(sourceInfo);
        mFontSize = fontSize;
    }

    public void applyStyle(JComponent component) {
        Font currentFont = component.getFont();
        component.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), mFontSize));
    }
}
