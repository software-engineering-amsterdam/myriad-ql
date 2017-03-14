/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/StyleSheet.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.visitorinterfaces.StyleSheetVisitor;

import java.util.List;

public class StyleSheet extends Node {

    private final String styleSheetName;
    private final List<Section> sections;
    private final List<DefaultStyle> defaultStyle;

    public StyleSheet(List<Section> sections, List<DefaultStyle> defaults, String name, LineNumber lineNumber) {
        super(lineNumber);
        this.sections = sections;
        this.defaultStyle = defaults;
        this.styleSheetName = name;
    }

    public String getName() {
        return this.styleSheetName;
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public List<DefaultStyle> getDefaultStyle() {
        return this.defaultStyle;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
