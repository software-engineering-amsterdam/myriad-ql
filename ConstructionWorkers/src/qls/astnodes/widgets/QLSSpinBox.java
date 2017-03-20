/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSSpinBox.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.MoneyType;
import ql.astnodes.types.StringType;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

import java.util.*;
import java.util.List;

public class QLSSpinBox extends QLSWidget {

    public QLSSpinBox(String label, LineNumber lineNumber) {
        super(lineNumber);
        widgetLabel.setText(label);
    }

    @Override
    public List<Type> getSupportedQuestionTypes() {
        List<Type> supportedQuestionTypes = new ArrayList<>();
        // TODO: string type is supported here. Also support string type for dropdown widget?
        supportedQuestionTypes.add(new StringType());
        supportedQuestionTypes.add(new IntegerType());
        supportedQuestionTypes.add(new MoneyType());
        return supportedQuestionTypes;
    }

    public <T> T accept(StyleAndWidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
