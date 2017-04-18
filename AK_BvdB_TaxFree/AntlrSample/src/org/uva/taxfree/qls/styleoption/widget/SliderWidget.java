package org.uva.taxfree.qls.styleoption.widget;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.IntValue;
import org.uva.taxfree.ql.model.values.Value;

import javax.swing.*;

public class SliderWidget extends WidgetStyleOption {

    private final JSlider mSlider;

    public SliderWidget(SourceInfo sourceInfo) {
        super(sourceInfo);
        mSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    }

    @Override
    public Value resolve() {
        return new IntValue(mSlider.getValue());
    }

    @Override
    protected JComponent generateComponent() {
        return mSlider;
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mSlider.addChangeListener(unusedEvent -> listener.updateForm());
    }

    @Override
    public boolean supports(Type supportedType) {
        return supportedType.equals(new IntegerType());
    }
}
