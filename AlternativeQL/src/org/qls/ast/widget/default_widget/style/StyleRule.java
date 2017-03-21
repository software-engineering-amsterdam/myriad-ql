package org.qls.ast.widget.default_widget.style;

import org.ql.ast.Node;

/**
 * hashCode() and equals() use instance's class because style rules cannot be duplicated for a widget
 */
public abstract class StyleRule extends Node {
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
