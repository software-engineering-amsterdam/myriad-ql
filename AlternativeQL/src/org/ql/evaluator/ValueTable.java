package org.ql.evaluator;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueTable {
    private final Map<String, Value> values = new HashMap<>();

    public Value declare(Identifier id, Value value) {
        return values.put(id.toString(), value);
    }

    public Value lookup(Identifier id) {
        if (!this.isDeclared(id)) {
            return new UnknownValue();
        }
        return values.get(id.toString());
    }

    public boolean isDeclared(Identifier id) {
        return values.containsKey(id.toString());
    }

    public int size() {
        return values.size();
    }

    public boolean equals(ValueTable obj) {
        if (size() + obj.size() == 0) {
            return true;
        }

        return values.equals(obj.values);
    }

    public ValueTable copy() {
        ValueTable valueTable = new ValueTable();
        valueTable.values.putAll(values);
        return valueTable;
    }
}
