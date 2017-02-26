package GUI.widgets;

import GUI.GUIComponents.FormFrame;
import semanticChecker.formDataStorage.valueData.values.Value;

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
