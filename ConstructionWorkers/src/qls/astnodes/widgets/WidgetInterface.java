package qls.astnodes.widgets;

import ql.gui.components.FormFrame;
import ql.gui.formenvironment.values.Value;

import java.util.EventListener;

/**
 * Created by LGGX on 09-Mar-17.
 */
public interface WidgetInterface {

    void render(FormFrame form);
    void suppress(FormFrame form);

    Value getValue();
    void setValue(Value value);
    void setReadOnly(boolean isReadonly);
    void addListener(EventListener listener);
}
