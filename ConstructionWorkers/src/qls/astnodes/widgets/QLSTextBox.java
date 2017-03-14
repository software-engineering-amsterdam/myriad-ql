/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSTextBox.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.StringType;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

import java.util.*;
import java.util.List;

public class QLSTextBox extends QLSWidget {

    public QLSTextBox(String label, LineNumber lineNumber) {
        super(lineNumber);
        this.widgetLabel.setText(label);

    }

    @Override
    public void setLabel(String label) {
        this.widgetLabel.setText(label);
    }

    @Override
    public List<Type> getQuestionTypes() {
        List<Type> supportedTypes = new ArrayList<>();
        supportedTypes.add(new StringType());
        return supportedTypes;
    }

    public <T> T accept(StyleAndWidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
