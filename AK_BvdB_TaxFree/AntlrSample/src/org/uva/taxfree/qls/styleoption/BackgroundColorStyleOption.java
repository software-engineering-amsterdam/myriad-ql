package org.uva.taxfree.qls.styleoption;

import javax.swing.*;
import java.awt.*;

public class BackgroundColorStyleOption extends StyleOption {
    private final String mColorValue;

    public BackgroundColorStyleOption(String colorValue) {
        mColorValue = colorValue;
    }

    public void applyStyle(JComponent component) {
        component.setBackground(Color.decode(mColorValue));
    }
}
