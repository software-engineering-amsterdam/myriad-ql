package org.uva.taxfree.qls.styleoption;

import javax.swing.*;
import java.awt.*;

public class ColorStyleOption extends StyleOption {
    private final String mColorValue;

    public ColorStyleOption(String colorValue) {
        mColorValue = colorValue;
    }

    public void applyStyle(JComponent component) {
        component.setForeground(Color.decode(mColorValue));
    }
}
