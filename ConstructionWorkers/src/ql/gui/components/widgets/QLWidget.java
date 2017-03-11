/**
 * QLWidget.java.
 */

package ql.gui.components.widgets;

import ql.gui.components.FormFrame;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.util.EventListener;

public abstract class QLWidget {

    protected JComponent component;

    public QLWidget() {
        component = new JPanel();
    }

    public abstract Value getValue();

    public abstract void setValue(Value value);

    public abstract void setReadOnly(boolean isReadonly);

    public abstract void addListener(EventListener listener);

    public void render(FormFrame form) {
        form.addWidget(component);
    }

    public void remove(FormFrame form) {
        form.removeWidget(component);
    }

}
