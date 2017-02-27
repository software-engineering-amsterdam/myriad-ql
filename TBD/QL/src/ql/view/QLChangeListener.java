package ql.view;

import javafx.beans.value.ChangeListener;
import ql.ast.values.BooleanValue;
import ql.ast.values.FloatValue;
import ql.ast.values.IntValue;
import ql.ast.values.StringValue;
import ql.ast.visistor.environment.Environment;

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
}
