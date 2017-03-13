package qls.ast;

import qls.ast.literals.QLSIdent;
import qls.ast.widgets.Widget;

/**
 * Created by rico on 7-3-17.
 */
public class Question extends Statement {
    private final String id;
    private final Widget widget;

    public Question(QLSIdent id, Widget widget) {
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
