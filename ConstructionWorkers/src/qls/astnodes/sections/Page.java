/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/section/Page.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import qls.visitorinterfaces.StyleSheetVisitor;

import java.util.List;

public class Page extends AbstractSection {

    public Page(String sectionName, List<Section> sections, List<DefaultStyle> defaultStyles,
                LineNumber linenumber) {
        super(sectionName, sections, defaultStyles, linenumber);
    }

    public void accept(StyleSheetVisitor visitor) {
        visitor.visit(this);
    }
}
