/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSWidget.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

import javax.swing.*;
import java.util.List;

public abstract class QLSWidget extends Node {

    JLabel widgetLabel;

    // TODO: why is there an empty constructor?
    public QLSWidget() {

    }

    public QLSWidget(LineNumber lineNumber) {
        super(lineNumber);
        widgetLabel = new JLabel();
    }

    public void setLabel(String label) {
        widgetLabel.setText(label);
    }

    public abstract List<Type> getSupportedQuestionTypes();

    public boolean isUndefined() {
        return false;
    }

    public abstract <T> T accept(StyleAndWidgetVisitor<T> visitor);
}
