package GUI.widgets;

import GUI.GUIComponents.FormFrame;

import javax.swing.*;

/**
 * Created by LGGX on 24-Feb-17.
 */
public abstract class AbstractWidget implements Widget {

    protected JComponent component;

    public AbstractWidget() {
        this.component = new JPanel();
    }

    @Override
    public void render(FormFrame form) {
        form.addWidget(this.component);
    }

    @Override
    public void suppress(FormFrame form) {
        form.removeWidget(this.component);
    }
}
