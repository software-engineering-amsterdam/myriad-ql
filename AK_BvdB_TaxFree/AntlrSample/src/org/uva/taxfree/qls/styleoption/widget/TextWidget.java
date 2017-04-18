package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.StringType;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.IntValue;
import org.uva.taxfree.ql.model.values.StringValue;
import org.uva.taxfree.ql.model.values.Value;

import javax.swing.*;
import java.awt.*;

public class TextWidget extends WidgetStyleOption {

    private JFormattedTextField mTextField;

    public TextWidget(SourceInfo sourceInfo) {
        super(sourceInfo);
    }

    @Override
    public void applyStyle(Widget widget) {
        mTextField = generateTextField(widget.resolveValue());
        mTextField.setPreferredSize(new Dimension(100, 25));
        super.applyStyle(widget);
    }

    @Override
    public Value resolve() {
        return new StringValue(String.valueOf(mTextField.getValue()));
    }

    protected JFormattedTextField generateTextField(Value value) {
        if (new IntValue(0).equals(value)) {
            return new JFormattedTextField(0);
        }
        return new JFormattedTextField("");

    }

    @Override
    protected JComponent generateComponent() {
        return mTextField;
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mTextField.addPropertyChangeListener(unusedEvent -> listener.updateForm());
    }

    @Override
    public boolean supports(Type supportedType) {
        return supportedType.equals(new IntegerType()) || supportedType.equals(new StringType());
    }
}
