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

public interface StyleSheetVisitor<T> {

    T visit(StyleSheet styleSheet);

    T visit(Section section);
    T visit(DefaultStyle section);

    T visit(StyleQuestion question);
}
