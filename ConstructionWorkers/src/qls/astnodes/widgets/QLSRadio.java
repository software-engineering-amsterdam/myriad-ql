package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.StringType;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.Style;
import qls.astnodes.visitors.StyleSheetVisitor;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by LGGX on 04-Mar-17.
 */
public class QLSRadio extends QLSWidget {

    private String actionCommandValue;
    private String yesLabel;
    private String noLabel;

    private ButtonGroup radioButtonGroup;
    private JRadioButton yesBtn;
    private JRadioButton noBtn;

    public QLSRadio() {

    }

    public QLSRadio(String label, String yes, String no, LineNumber lineNumber) {
        super(lineNumber);
        this.yesLabel = yes;
        this.noLabel = no;

        this.componentLabel.setText(label);

        this.yesBtn = new JRadioButton(yes);
        this.noBtn = new JRadioButton(no);
        this.radioButtonGroup = new ButtonGroup();

        this.radioButtonGroup.add(this.yesBtn);
        this.radioButtonGroup.add(this.noBtn);
        this.component.add(this.componentLabel);
        this.component.add(this.yesBtn);
        this.component.add(this.noBtn);

    }

    @Override
    public void applyStyle(Style style) {
        style.AddDefaultInheritedStyles(this.getDefaultStyle());

        Font font = new Font(
                style.getFont(this.getDefaultFont().getValue()), 0,
                style.getFontSize(Integer.parseInt(this.getDefaultFontSize().getValue()))
        );
        this.componentLabel.setFont(font);

        Color color = style.getColor(Integer.parseInt(this.getDefaultColor().getValue()));
        this.componentLabel.setForeground(color);

        this.yesBtn.setPreferredSize(new Dimension(
                Integer.parseInt(this.getDefaultWidth().getValue()),
                (int) this.yesBtn.getPreferredSize().getHeight()
        ));
        this.noBtn.setPreferredSize(new Dimension(
                Integer.parseInt(this.getDefaultWidth().getValue()),
                (int) this.noBtn.getPreferredSize().getHeight()
        ));
    }

    @Override
    public void addListener(EventListener listener) {
        this.yesBtn.addActionListener(e -> actionCommandValue = e.getActionCommand());

        this.noBtn.addActionListener(e -> actionCommandValue = e.getActionCommand());
    }

    @Override
    public BooleanValue getValue() {
        if (this.actionCommandValue.equals(this.yesLabel)) {
            return new BooleanValue(true);
        } else if (this.actionCommandValue.equals(this.noLabel)) {
            return new BooleanValue(false);
        }
        return new BooleanValue(false);
    }

    @Override
    public void setValue(Value nvalue) {
        BooleanValue value = (BooleanValue) nvalue;
        if (value.getValue().equals(true)) {
            this.yesBtn.setSelected(true);
            this.noBtn.setSelected(false);
        } else {
            this.yesBtn.setSelected(false);
            this.noBtn.setSelected(true);
        }
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.component.setEnabled(false);
    }


    public List<Type> getSupportedQuestionTypes() {
        List<Type> supportedTypes = new ArrayList<>();
        supportedTypes.add(new StringType());
        supportedTypes.add(new BooleanType());
        return supportedTypes;
    }


    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
