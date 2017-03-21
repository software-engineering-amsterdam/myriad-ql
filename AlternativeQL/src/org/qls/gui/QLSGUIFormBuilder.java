package org.qls.gui;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.WidgetContainer;
import org.qls.ast.StyleSheet;
import org.qls.gui.widgets.CustomWidgetContainer;

public class QLSGUIFormBuilder implements ValueReviser {

    private final Window window;
    private final StyleSheet styleSheet;
    private final PageBuilder pageBuilder;

    public QLSGUIFormBuilder(Window window, Form form, StyleSheet styleSheet) {
        this.window = window;
        this.styleSheet = styleSheet;
        pageBuilder = new PageBuilder(new WidgetContainer(this), form, new CustomWidgetContainer(this));
    }

    @Override
    public void reviseValue(Identifier identifier, Value newValue) {
        // TODO updates a form value (in the value table)
    }

    public void constructFormPage(int pageNumber) {
        window.reset();
        window.addSections(pageBuilder.createSections(styleSheet.getPage(pageNumber)));
    }
}
