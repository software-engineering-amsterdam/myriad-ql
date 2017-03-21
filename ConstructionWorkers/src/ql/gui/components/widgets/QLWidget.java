/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/widgets/QLWidget.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.components.widgets;

import ql.gui.components.FormFrame;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.util.EventListener;

public abstract class QLWidget {

    JComponent component;

    public QLWidget() {
        component = new JPanel();
    }

    public abstract Value getValue();

    public abstract void setValue(Value value);

    public abstract void setReadOnly();

    public abstract void addListener(EventListener listener);

    public void render(FormFrame form) {
        form.addWidget(component);
    }

    public void remove(FormFrame form) {
        form.removeWidget(component);
    }
}
