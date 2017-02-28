package ql.view;

import javafx.beans.value.ChangeListener;
import ql.ast.values.*;
import ql.ast.environment.Environment;

/**
 * Created by rico on 27-2-17.
 */
public abstract class QLChangeListener<T> implements ChangeListener<T> {
    private final String key;
    private final Environment environment;

    public QLChangeListener(Environment environment, String key) {
        this.environment = environment;
        this.key = key;
    }

    public void setValue(String value) {
        if (value.isEmpty() || value.equals("")){
            environment.setVariableValue(key, new UndefinedValue());
        }
        environment.setVariableValue(key, new StringValue(value));
    }

    public void setValue(boolean value) {
        environment.setVariableValue(key, new BooleanValue(value));
    }

    public void setValue(int value) {
        environment.setVariableValue(key, new IntValue(value));
    }

    public void setValue(float value) {
        environment.setVariableValue(key, new FloatValue(value));
    }

    public void setValueUndefined() {
        environment.setVariableValue(key, new UndefinedValue());
    }
}
