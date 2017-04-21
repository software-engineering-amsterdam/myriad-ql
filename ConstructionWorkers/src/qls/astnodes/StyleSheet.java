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
import qls.astnodes.sections.Page;
import qls.astnodes.sections.Section;
import qls.visitorinterfaces.StyleSheetVisitor;

import java.util.List;

public class StyleSheet extends Node {

    private final String styleSheetName;
    private final List<Page> pages;

    public StyleSheet(String styleSheetName, List<Page> pages, LineNumber lineNumber) {
        super(lineNumber);
        this.styleSheetName = styleSheetName;
        this.pages = pages;
    }

    public String getName() {
        return styleSheetName;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void accept(StyleSheetVisitor visitor) {
        visitor.visit(this);
    }
}
