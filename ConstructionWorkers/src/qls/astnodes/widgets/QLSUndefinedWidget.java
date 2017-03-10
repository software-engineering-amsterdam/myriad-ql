package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;
import ql.gui.components.FormFrame;
import ql.gui.formenvironment.values.UndefinedValue;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.Style;
import qls.astnodes.visitors.StyleSheetVisitor;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Created by LGGX on 04-Mar-17.
 */
public class QLSUndefinedWidget extends QLSWidget {

    public QLSUndefinedWidget(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public void applyStyle(Style style) {
        throw new AssertionError();
    }

    @Override
    public boolean isUndefined() {
        return true;
    }

    public List<Type> getSupportedQuestionTypes() {
        return new ArrayList<>();
    }

    @Override
    public void render(FormFrame form) {}

    @Override
    public void suppress(FormFrame canvas) {}

    @Override
    public UndefinedValue getValue() {
        return null;
    }

    @Override
    public void setValue(Value value) {}

    @Override
    public void setReadOnly(boolean _isReadonly) {}

    @Override
    public void addListener(EventListener listener) {}

    @Override
    public void setLabel(String _label) {}

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
