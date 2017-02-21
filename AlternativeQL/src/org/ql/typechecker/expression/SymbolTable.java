package org.ql.typechecker.expression;

import org.ql.ast.Identifier;
import org.ql.ast.type.Type;

import java.util.HashMap;

public class SymbolTable extends HashMap<String, Type> {
    public Type put(Identifier id, Type value) {
        return super.put(id.toString(), value);
    }

    public Type get(Identifier id) {
        return super.get(id.toString());
    }

    public boolean containsKey(Identifier id) {
        return super.containsKey(id.toString());
    }
}
