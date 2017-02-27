package QL.GUI.GUIComponents.GUIWidgets;

import QL.GUI.GUIComponents.FormFrame;
import QL.semanticChecker.formDataStorage.valueData.values.Value;

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
