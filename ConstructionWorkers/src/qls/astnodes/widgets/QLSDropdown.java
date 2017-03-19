/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/widgets/QLSDropdown.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.Type;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

import java.util.*;
import java.util.List;

public class QLSDropdown extends QLSWidget {

    private String yesLabel;
    private String noLabel;

    public QLSDropdown(String label, String yes, String no, LineNumber lineNumber) {
        super(lineNumber);
        this.widgetLabel.setText(label);
        this.yesLabel = yes;
        this.noLabel = no;
    }

    @Override
    public void setLabel(String label) {
        this.widgetLabel.setText(label);
    }

    @Override
    public List<Type> getQuestionTypes() {
        List<Type> supportedTypes = new ArrayList<>();
        supportedTypes.add(new BooleanType());
        return supportedTypes;
    }

    public <T> T accept(StyleAndWidgetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}