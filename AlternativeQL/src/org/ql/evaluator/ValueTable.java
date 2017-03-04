package org.ql.evaluator;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueTable {
    private final Map<String, Value> values = new HashMap<>();

    public Value declare(Identifier id, Value value) {
        return values.put(id.toString(), value);
    }

    public Value lookup(Identifier id) {
        return values.get(id.toString());
    }

    public boolean isDeclared(Identifier id) {
        return values.containsKey(id.toString());
    }

    public int size() {
        return values.size();
    }
}
