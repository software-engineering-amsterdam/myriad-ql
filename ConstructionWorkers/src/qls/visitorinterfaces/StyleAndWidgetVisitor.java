/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/visitorinterfaces/StyleAndWidgetVisitor.java.
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
import qls.astnodes.widgets.*;

public interface StyleAndWidgetVisitor<T> {
    T visit(Color style);
    T visit(Width style);
    T visit(Font style);
    T visit(FontSize style);

    T visit(QLSCheckBox widget);
    T visit(QLSRadio widget);
    T visit(QLSSlider widget);
    T visit(QLSSpinBox widget);
    T visit(QLSTextBox widget);
    T visit(QLSDropdown widget);
    T visit(QLSUndefinedWidget widget);
}
