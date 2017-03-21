package org.qls.ast.widget;

import org.ql.ast.Node;
import org.ql.ast.statement.Question;
import org.ql.ast.type.*;
import org.qls.gui.widgets.CustomWidgetContainer;

public abstract class Widget extends Node implements TypeCompatibility {
    @Override
    public boolean isCompatibleWith(TypeCompatibility type) {
        throw new AssertionError();
    }

    public abstract <T, C> T accept(WidgetVisitor<T, C> visitor, C context);
}
