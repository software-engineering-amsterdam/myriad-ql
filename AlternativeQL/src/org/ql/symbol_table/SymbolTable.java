package org.ql.symbol_table;

import org.ql.ast.Identifier;
import org.ql.ast.type.Type;

public interface SymbolTable {
    Type declare(Identifier id, Type value);

    Type lookup(Identifier id);

    boolean has(Identifier id);

    int size();
}
