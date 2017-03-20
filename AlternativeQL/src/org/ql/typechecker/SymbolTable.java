package org.ql.typechecker;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.type.Type;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, Type> definitions = new HashMap<>();

    public Type declare(Identifier id, Type value) {
        return definitions.put(id.toString(), value);
    }

    public Type lookup(Identifier id) {
        return definitions.get(id.toString());
    }

    public boolean isDeclared(Identifier id) {
        return definitions.containsKey(id.toString());
    }

    public int size() {
        return definitions.size();
    }
}
