package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.widgets.Resolvable;
import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.qls.styleoption.StyleOption;

import javax.swing.*;

public abstract class WidgetStyleOption extends StyleOption implements Resolvable {

    public WidgetStyleOption(SourceInfo sourceInfo) {
        super(sourceInfo);
    }

    @Override
    public void applyStyle(Widget widget) {
        widget.setValueCompoent(generateComponent());
        widget.setResolver(this);
    }

    protected abstract JComponent generateComponent();
}
