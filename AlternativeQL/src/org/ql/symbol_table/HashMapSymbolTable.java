package org.ql.symbol_table;

import org.ql.ast.Identifier;
import org.ql.ast.type.Type;

import java.util.HashMap;
import java.util.Map;

public class HashMapSymbolTable implements SymbolTable {

    private final Map<String, Type> definitions = new HashMap<>();

    public Type put(Identifier id, Type value) {
        return definitions.put(id.toString(), value);
    }

    public Type get(Identifier id) {
        return definitions.get(id.toString());
    }

    public boolean has(Identifier id) {
        return definitions.containsKey(id.toString());
    }

    @Override
    public int size() {
        return definitions.size();
    }
}
