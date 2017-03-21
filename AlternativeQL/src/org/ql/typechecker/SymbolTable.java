package org.ql.typechecker;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.type.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

    private final Map<Identifier, Type> definitions = new HashMap<>();

    public Type declare(Identifier id, Type value) {
        return definitions.put(id, value);
    }

    public Type lookup(Identifier id) {
        return definitions.get(id);
    }

    public boolean isDeclared(Identifier id) {
        return definitions.containsKey(id);
    }

    public Set<Identifier> identifiers() {
        return definitions.keySet();
    }

    public int size() {
        return definitions.size();
    }
}
