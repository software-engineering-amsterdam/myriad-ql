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

    protected JLabel widgetLabel;

    public QLSWidget(){
    }

    public QLSWidget(LineNumber lineNumber) {
        super(lineNumber);
        this.widgetLabel = new JLabel();
    }

    public void setLabel(String label) {
        this.widgetLabel.setText(label);
    }

    public abstract List<Type> getQuestionTypes();

    public abstract <T> T accept(StyleAndWidgetVisitor<T> visitor);

    public boolean isUndefined() {
        return false;
    }
}
