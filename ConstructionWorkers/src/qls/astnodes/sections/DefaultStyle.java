/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/section/DefaultStyle.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;
import qls.astnodes.styles.Style;
import qls.visitorinterfaces.StyleSheetVisitor;
import qls.astnodes.widgets.QLSWidget;

public class DefaultStyle extends AbstractSection {

    private final Type questionType;
    private Style style;
    private final QLSWidget widget;

    public DefaultStyle(Type questionType, Style style, QLSWidget widget, LineNumber lineNumber) {
        super(lineNumber);
        this.questionType = questionType;
        this.style = style;
        this.widget = widget;
    }

    public Type getQuestionType() {
        return questionType;
    }

    public Style getStyle() {
        return style;
    }

    public QLSWidget getWidget() {
        return widget;
    }

    public void accept(StyleSheetVisitor visitor) {
        visitor.visit(this);
    }
}
