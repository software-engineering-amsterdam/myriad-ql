package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.values.IntValue;
import org.uva.taxfree.ql.model.values.Value;

import javax.swing.*;

public class SpinboxWidget extends WidgetStyleOption {
    private final JSpinner mSpinner;
    private final SpinnerNumberModel mModel;

    public SpinboxWidget(SourceInfo sourceInfo) {
        super(sourceInfo);
        mModel = new SpinnerNumberModel(0, 0, 100, 1);
        mSpinner = new JSpinner(mModel);

    }

    @Override
    public Value resolve() {
        return new IntValue(mModel.getNumber().intValue());
    }

    @Override
    protected JComponent generateComponent() {
        return mSpinner;
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mSpinner.addChangeListener(unusedEvent -> listener.updateForm());
    }
}
