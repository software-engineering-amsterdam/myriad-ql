/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/widgets/WidgetFactory.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.components.widgets;

import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.types.*;
import ql.visitorinterfaces.TypeVisitor;

public class WidgetFactory implements TypeVisitor<QLWidget> {

    private String questionLabel;

    public QLWidget getWidgetForQuestion(SimpleQuestion question) {
        questionLabel = question.getLabel();
        Type type = question.getType();
        return type.accept(this);
    }

    public QLWidget visit(BooleanType type) {
        return new Checkbox(questionLabel);
    }

    public QLWidget visit(IntegerType type) {
        return new IntegerTextField(questionLabel);
    }

    public QLWidget visit(MoneyType type) {
        return new MoneyTextField(questionLabel);
    }

    public QLWidget visit(StringType type) {
        return new TextField(questionLabel);
    }
}
