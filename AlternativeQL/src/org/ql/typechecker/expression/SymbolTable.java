package org.ql.typechecker.expression;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;

import java.util.HashMap;
import java.util.List;

public interface SymbolTable {
    Type put(Identifier id, Type value);
    Type get(Identifier id);
    boolean has(Identifier id);
}
