package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.values.Value;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentsTable {

    private final Map<String, Value> values = new HashMap<>();

    public Value add(String id, Value value) {
        return values.put(id, value);
    }

    public Value find(String id) {
        return values.get(id);
    }

    public boolean contains(String id) {
        return values.containsKey(id);
    }

    public int size() {
        return values.size();
    }

}
