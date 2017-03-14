package org.uva.hatt.taxform.ast.visitors;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentsTable {

    private final Map<String, String> values = new HashMap<>();

    public String add(String id, String value) {
        return values.put(id, value);
    }

    public String find(String id) {
        return values.get(id);
    }

    public boolean contains(String id) {
        return values.containsKey(id);
    }

    public int size() {
        return values.size();
    }

}
