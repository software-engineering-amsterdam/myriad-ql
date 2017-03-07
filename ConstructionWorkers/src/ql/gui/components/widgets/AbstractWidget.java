/**
 * AbstractWidget.java.
 */

package ql.gui.components.widgets;

import ql.gui.components.FormFrame;

import javax.swing.*;

public abstract class AbstractWidget implements WidgetInterface {

    protected JComponent component;

    public AbstractWidget() {
        component = new JPanel();
    }

    @Override
    public void render(FormFrame form) {
        form.addWidget(component);
    }

    @Override
    public void suppress(FormFrame form) {
        form.removeWidget(component);
    }
}
