package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;
import qls.astnodes.styles.Style;
import qls.astnodes.visitors.StyleSheetVisitor;
import qls.astnodes.widgets.QLSWidget;

/**
 * Created by LGGX on 02-Mar-17.
 */
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

    public void setStyle(Style style) {
        this.style = style;
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
