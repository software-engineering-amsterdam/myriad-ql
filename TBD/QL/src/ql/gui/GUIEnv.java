package ql.gui;

import ql.values.UndefinedValue;
import ql.values.Value;

import java.util.HashMap;

/**
 * Created by Erik on 20-3-2017.
 */
public class GUIEnv {
    private HashMap<String, Value> variables = new HashMap<>();

    public Value<?> getValue(String key) {
        if (variables.containsKey(key)) {
            return variables.get(key);
        }
        return new UndefinedValue();
    }

    public void setValue(String key, Value<?> value) {
        variables.put(key, value);
    }
}
