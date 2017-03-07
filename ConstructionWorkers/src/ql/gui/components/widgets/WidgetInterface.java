/**
 * WidgetInterface.java.
 */

package ql.gui.components.widgets;

import ql.gui.components.FormFrame;
import ql.gui.formenvironment.values.Value;

import java.util.EventListener;

public interface WidgetInterface {

    Value getValue();

    void setValue(Value value);

    void setReadOnly(boolean isReadonly);

    void addListener(EventListener listener);

    void render(FormFrame form);

    void suppress(FormFrame form);
}
