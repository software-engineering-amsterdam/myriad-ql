package ql.gui.components.widgets;

import ql.gui.components.FormFrame;
import ql.gui.formenvironment.values.Value;

import java.util.EventListener;

/**
 * Created by LGGX on 23-Feb-17.
 */
public interface Widget {

    void setValue(Value value);

    Object getValue();

    void addListener(EventListener listener);

    void render(FormFrame form);
    void suppress(FormFrame form);

    void setReadOnly(boolean _isReadonly);

}
