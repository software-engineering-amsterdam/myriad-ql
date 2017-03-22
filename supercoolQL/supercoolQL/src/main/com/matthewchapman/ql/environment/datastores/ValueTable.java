package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.environment.values.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 20/03/2017.
 */
public class ValueTable {

    private final Map<String, Value> values;

    public ValueTable() {
        values = new HashMap<>();
    }

    public void addOrUpdateValue(String name, Value value) {
        values.put(name, value);
    }

    public Value getValueByID(String name) {
        return values.get(name);
    }
}
