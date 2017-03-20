/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSCheckbox.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.StringType;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

import java.util.ArrayList;
import java.util.List;

public class QLSCheckBox extends QLSWidget {

    // TODO: why is there an empty constructor?
    public QLSCheckBox() {

    }

    public QLSCheckBox(String label, LineNumber lineNumber) {
        super(lineNumber);
        widgetLabel.setText(label);
    }

    @Override
    public List<Type> getSupportedQuestionTypes() {
        List<Type> supportedQuestionTypes = new ArrayList<>();
        // TODO: support string type?
        supportedQuestionTypes.add(new StringType());
        supportedQuestionTypes.add(new BooleanType());
        return supportedQuestionTypes;
    }

    public <T> T accept(StyleAndWidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
