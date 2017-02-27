package org.ql.symbol_table;

import org.ql.ast.Identifier;
import org.ql.ast.type.Type;

public interface SymbolTable {
    Type put(Identifier id, Type value);

    Type get(Identifier id);

    boolean has(Identifier id);

    int size();
}
