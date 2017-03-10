package qls.astnodes.visitors;

import qls.astnodes.StyleSheet;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.astnodes.StyleQuestion;
import qls.astnodes.styles.Color;
import qls.astnodes.styles.Font;
import qls.astnodes.styles.FontSize;
import qls.astnodes.styles.Width;
import qls.astnodes.widgets.*;

/**
 * Created by LGGX on 02-Mar-17.
 */
public interface StyleSheetVisitor<T> {

    T visit(StyleSheet styleSheet);

    T visit(Section section);
    T visit(DefaultStyle section);

    T visit(StyleQuestion question);

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
