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

    public StyleSheet(String styleSheetName, List<Section> sections, List<DefaultStyle> defaults, LineNumber lineNumber) {
        super(lineNumber);
        this.styleSheetName = styleSheetName;
        this.sections = sections;
        defaultStyle = defaults;
    }

    public String getName() {
        return styleSheetName;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<DefaultStyle> getDefaultStyle() {
        return defaultStyle;
    }

    public void accept(StyleSheetVisitor visitor) {
        visitor.visit(this);
    }
}
