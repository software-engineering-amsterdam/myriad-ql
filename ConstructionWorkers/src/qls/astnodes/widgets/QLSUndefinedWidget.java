/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSUndefinedWidget.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */
package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

import java.util.ArrayList;
import java.util.List;


public class QLSUndefinedWidget extends QLSWidget {

    public QLSUndefinedWidget(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public boolean isUndefined() {
        return true;
    }

    @Override
    public List<Type> getQuestionTypes() {
        return new ArrayList<>();
    }

    public <T> T accept(StyleAndWidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
