package qls.ast;

import qls.ast.attributes.widgets.Widget;
import qls.ast.literals.QLSIdent;

/**
 * Created by Erik on 13-3-2017.
 */
public class QuestionWidget extends Statement {
    private final String id;
    private final Widget widget;



    public QuestionWidget(QLSIdent id, Widget widget) {
        super(id.getRowNumber());
        this.id = id.getValue();
        this.widget = widget;
    }

    public String getId() {
        return id;
    }

    public Widget getWidget() {
        return widget;
    }
}
