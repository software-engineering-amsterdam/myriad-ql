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

public class Style extends Node{

    private final List<StyleType> styleTypes;

    public Style(LineNumber lineNumber) {
        super(lineNumber);
        styleTypes = new ArrayList<>();
    }

    public Style(List<StyleType> styleTypes, LineNumber lineNumber) {
        super(lineNumber);
        this.styleTypes = styleTypes;
    }

    public int getWidth(int styleValue) {
        for (StyleType styleProperty : this.styleTypes) {
            if (styleProperty.getName().equals("width")) {
                return Integer.parseInt(styleProperty.getValue());
            }
        }
        return styleValue;
    }

    public String getFont(String styleValue) {
        for (StyleType styleProperty : this.styleTypes) {
            if (styleProperty.getName().equals("font")) {
                return styleProperty.getValue();
            }
        }
        return styleValue;
    }

    public int getFontSize(int styleValue) {
        for (StyleType styleProperty : this.styleTypes) {
            if (styleProperty.getName().equals("fontsize")) {
                return Integer.parseInt(styleProperty.getValue());
            }
        }
        return styleValue;
    }

    public java.awt.Color getColor(int styleValue) {
        for (StyleType styleProperty : this.styleTypes) {
            if (styleProperty.getName().equals("color")) {
                return new java.awt.Color(Integer.parseInt(styleProperty.getValue()));
            }
        }
        return new java.awt.Color(styleValue);
    }

    public boolean isUndefined() {
        return false;
    }

}
