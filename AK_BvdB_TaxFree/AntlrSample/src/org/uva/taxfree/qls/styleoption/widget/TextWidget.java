package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.values.StringValue;
import org.uva.taxfree.ql.model.values.Value;

import javax.swing.*;

public class TextWidget extends WidgetStyleOption {

    private final JFormattedTextField mTextField;

    public TextWidget(SourceInfo sourceInfo) {
        super(sourceInfo);
        mTextField = new JFormattedTextField("");
    }

    @Override
    public Value resolve() {
        return new StringValue(String.valueOf(mTextField.getValue()));
    }

    @Override
    protected JComponent generateComponent() {
        return mTextField;
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mTextField.addPropertyChangeListener(unusedEvent -> listener.updateForm());
    }
}
