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

public class DefaultStyle extends AbstractSection{

    private Style style;
    private final QLSWidget widget;
    private final Type questionType;

    public DefaultStyle(Style style, QLSWidget widget, Type questionType, LineNumber lineNumber)
    {
        super(lineNumber);
        this.style = style;
        this.widget = widget;
        this.questionType = questionType;
    }

    public Style getStyle() {
        return this.style;
    }

    public QLSWidget getWidget() {
        return widget;
    }

    public Type getQuestionType() {
        return questionType;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
