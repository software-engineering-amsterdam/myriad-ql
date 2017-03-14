/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/sections/StyleQuestion.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import qls.visitorinterfaces.StyleSheetVisitor;
import qls.astnodes.widgets.QLSWidget;

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
