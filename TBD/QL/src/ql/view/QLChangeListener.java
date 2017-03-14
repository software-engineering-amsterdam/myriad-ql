package ql.view;

import javafx.beans.value.ChangeListener;
import ql.ast.environment.Env;
import ql.ast.values.*;

/**
 * Created by rico on 27-2-17.
 */
public abstract class QLChangeListener<T> implements ChangeListener<T> {
    private final String key;
    private final Env env;

    public QLChangeListener(Env env, String key) {
        this.env = env;
        this.key = key;
    }

    public void setValue(String value) {
        if (value.isEmpty() || value.equals("")){
            env.setQuestionValue(key, new UndefinedValue());
        }
        env.setQuestionValue(key, new StringValue(value));
    }

    public void setValue(boolean value) {
        env.setQuestionValue(key, new BooleanValue(value));
    }

    public void setValue(int value) {
        env.setQuestionValue(key, new IntValue(value));
    }

    public void setValue(float value) {
        env.setQuestionValue(key, new FloatValue(value));
    }

    public void setValueUndefined() {
        env.setQuestionValue(key, new UndefinedValue());
    }
}
