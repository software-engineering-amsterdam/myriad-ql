package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.model.SourceInfo;

import javax.swing.*;
import java.awt.*;

public class ColorStyleOption extends StyleOption {
    private final String mColorValue;

    public ColorStyleOption(String colorValue, SourceInfo sourceInfo) {
        super(sourceInfo);
        mColorValue = colorValue;
    }

    public void applyStyle(JComponent component) {
        component.setForeground(Color.decode(mColorValue));
    }
}
