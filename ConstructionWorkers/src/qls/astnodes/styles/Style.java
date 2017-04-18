/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/styles/Style.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.styles;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;

import java.util.ArrayList;
import java.util.List;

public class Style extends Node {

    private final List<StyleType> styleTypes;

    Style(LineNumber lineNumber) {
        super(lineNumber);
        styleTypes = new ArrayList<>();
    }

    public Style(List<StyleType> styleTypes, LineNumber lineNumber) {
        super(lineNumber);
        this.styleTypes = styleTypes;
    }

    public int getWidth(int defaultValue) {
        for (StyleType styleProperty : styleTypes) {
            if (styleProperty.getName().equals("width")) {
                return Integer.parseInt(styleProperty.getValue());
            }
        }
        return defaultValue;
    }

    public String getFont(String defaultValue) {
        for (StyleType styleProperty : styleTypes) {
            if (styleProperty.getName().equals("font")) {
                return styleProperty.getValue();
            }
        }
        return defaultValue;
    }

    public int getFontSize(int defaultValue) {
        for (StyleType styleProperty : styleTypes) {
            if (styleProperty.getName().equals("fontsize")) {
                return Integer.parseInt(styleProperty.getValue());
            }
        }
        return defaultValue;
    }

    public java.awt.Color getColor(int defaultValue) {
        for (StyleType styleProperty : styleTypes) {
            if (styleProperty.getName().equals("color")) {
                return new java.awt.Color(Integer.parseInt(styleProperty.getValue()));
            }
        }
        return new java.awt.Color(defaultValue);
    }

    public boolean isUndefined() {
        return false;
    }
}
