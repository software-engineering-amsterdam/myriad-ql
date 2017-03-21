/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/visitorinterfaces/StyleSheetVisitor.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.visitorinterfaces;

import qls.astnodes.StyleSheet;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.astnodes.sections.StyleQuestion;

public interface StyleSheetVisitor {
    void visit(StyleSheet styleSheet);
    void visit(Section section);
    void visit(DefaultStyle section);
    void visit(StyleQuestion question);
}
