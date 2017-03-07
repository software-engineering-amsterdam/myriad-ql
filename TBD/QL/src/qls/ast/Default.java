package qls.ast;

import qls.ast.widgets.Widget;

/**
 * Created by rico on 7-3-17.
 */
public class Default extends ASTNode {
    private final Widget widget;
    private final String type;

    public Default(String type, Widget widget, int rowNumber) {
        super(rowNumber);
        this.type = type;
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }

    public String getType() {
        return type;
    }
}
