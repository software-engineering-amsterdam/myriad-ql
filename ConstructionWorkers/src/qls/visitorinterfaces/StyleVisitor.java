/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/visitorinterfaces/StyleVisitor.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.visitorinterfaces;

import qls.astnodes.styles.Color;
import qls.astnodes.styles.Font;
import qls.astnodes.styles.FontSize;
import qls.astnodes.styles.Width;

public interface StyleVisitor<T> {
    T visit(Color style);
    T visit(Width style);
    T visit(Font style);
    T visit(FontSize style);
}
