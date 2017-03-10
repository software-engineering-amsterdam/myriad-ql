package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import qls.astnodes.visitors.StyleSheetVisitor;
import qls.astnodes.widgets.QLSWidget;

/**
 * Created by LGGX on 02-Mar-17.
 */
public class StyleQuestion extends AbstractSection{

    private final String identifierName;
    private QLSWidget widget;

    public StyleQuestion(String identifierName, QLSWidget widget, LineNumber lineNumber) {
        super(lineNumber);
        this.identifierName = identifierName;
        this.widget = widget;
    }

    public String getName() {
        return this.identifierName;
    }

    public void setWidget(QLSWidget widget) {
        this.widget = widget;
    }

    public QLSWidget getWidget() {
        return this.widget;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
