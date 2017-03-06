package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.values.IntegerValue;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.Style;
import qls.astnodes.visitors.StyleSheetVisitor;
import qls.astnodes.widgets.widgettypes.SliderType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * Created by LGGX on 04-Mar-17.
 */
public class QLSSlider extends QLSWidget {

    private static final int MIN = -1000;
    private static final int MAX = 1000;
    private static final int STEP = 1;

    private JSlider slider;
    private JLabel valueLabel;

    public QLSSlider() {

    }

    public QLSSlider(String _label, LineNumber lineNumber) {
        super(lineNumber);

        this.componentLabel.setText(_label);
        this.valueLabel = new JLabel("0");
        this.slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, STEP);

        this.component.add(this.componentLabel);
        this.component.add(this.slider);
        this.component.add(this.valueLabel);

        this.type = new SliderType();
    }

    @Override
    public void applyStyle(Style style) {
        style.getInheritedStyle(this.getDefaultStyle());

        Font font = new Font(
                style.getFont(this.getDefaultFont().getValue()), 0,
                style.getFontSize(Integer.parseInt(this.getDefaultFontSize().getValue()))
        );
        this.componentLabel.setFont(font);

        Color color = style.getColor(Integer.parseInt(this.getDefaultColor().getValue()));
        this.componentLabel.setForeground(color);

        this.slider.setPreferredSize(
                new Dimension(
                        Integer.parseInt(this.getDefaultWidth().getValue()),
                        (int) this.slider.getPreferredSize().getHeight()
                )
        );
    }

    @Override
    public void addListener(EventListener listener) {

        this.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int v = source.getValue();
                valueLabel.setText(Integer.toString(v));

            }
        });
    }

    @Override
    public IntegerValue getValue() {
        return new IntegerValue(this.slider.getValue());
    }

    @Override
    public void setValue(Value nvalue) {
        IntegerValue value = (IntegerValue) nvalue;
        this.slider.setValue(value.getValue());
        this.valueLabel.setText(Integer.toString(value.getValue()));
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.slider.setEnabled(_isReadonly);
    }

    public java.util.List<Type> getSupportedQuestionTypes() {
        java.util.List<Type> supportedTypes = new ArrayList<>(
                Arrays.asList(new IntegerType())
        );
        return supportedTypes;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
