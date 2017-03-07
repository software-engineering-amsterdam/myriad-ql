package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.astnodes.types.Type;
import ql.gui.components.FormFrame;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.*;
import qls.astnodes.visitors.StyleSheetVisitor;
import qls.astnodes.widgets.widgettypes.UndefinedWidgetType;
import qls.astnodes.widgets.widgettypes.WidgetType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Created by LGGX on 03-Mar-17.
 */
public abstract class QLSWidget extends Node implements WidgetInterface {

    protected final static String DEFAULT_FONT = "Arial";
    protected final static int DEFAULT_FONT_SIZE = 14;
    protected final static int DEFAULT_COLOR = 0x000000;
    protected final static int DEFAULT_WIDTH = 68;

    protected WidgetType type;
    protected JComponent component;
    protected JLabel componentLabel;

    public QLSWidget(){
    }

    public QLSWidget(LineNumber lineNumber) {
        super(lineNumber);
        this.type   = new UndefinedWidgetType();
        this.component = new JPanel();
        this.componentLabel = new JLabel();
    }

    public Style getDefaultStyle() {
        List<StyleType> defaultStyles = new ArrayList<>();
        defaultStyles.add(getDefaultFont());
        defaultStyles.add(getDefaultFontSize());
        defaultStyles.add(getDefaultColor());
        defaultStyles.add(getDefaultWidth());
        return new Style(defaultStyles, this.getLineNumber());
    }


    public Font getDefaultFont() {
        return new Font(DEFAULT_FONT, getLineNumber());
    }

    public FontSize getDefaultFontSize() {
        return new FontSize(DEFAULT_FONT_SIZE, getLineNumber());
    }

    public Color getDefaultColor() {
        return new Color(DEFAULT_COLOR, getLineNumber());
    }

    public Width getDefaultWidth() {
        return new Width(DEFAULT_WIDTH,getLineNumber());
    }

    public void setLabel(String _label) {
        this.componentLabel.setText(_label);
    }

    public WidgetType getType() {
        return this.type;
    }

    @Override
    public abstract void setValue(Value value);

    @Override
    public abstract Value getValue();

    @Override
    public abstract void addListener(EventListener listener);

    @Override
    public void render(FormFrame form) {
        form.addWidget(this.component);
    }

    @Override
    public void suppress(FormFrame form) {
        form.removeWidget(this.component);
    }

    @Override
    public abstract void setReadOnly(boolean isReadonly);

    public void resetStyleToDefault() {
        Style style = this.getDefaultStyle();
        this.applyStyle(style);
    }

    public abstract void applyStyle(Style _style);


    public abstract List<Type> getSupportedQuestionTypes();

    public abstract <T> T accept(StyleSheetVisitor<T> visitor);

    public boolean isUndefined() {
        return false;
    }
}
