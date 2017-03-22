package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.environment.values.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by matt on 20/03/2017.
 */
public class ValueTable extends Observable {

    private final Map<String, Value> values;

    public ValueTable() {
        values = new HashMap<>();
    }

    public void addOrUpdateValue(String name, Value value) {
        values.put(name, value);
        setChanged();
        notifyObservers();
    }

    public Value getValueByID(String name) {
        return values.get(name);
    }
}
