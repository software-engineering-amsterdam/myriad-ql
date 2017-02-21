package org.ql.typechecker.expression;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;

import java.util.HashMap;
import java.util.List;

public class SymbolTable extends HashMap<String, Type> {
    public SymbolTable() {}
    public SymbolTable(List<Question> questions) {
        putAll(questions);
    }

    public Type put(Identifier id, Type value) {
        return super.put(id.toString(), value);
    }

    public Type get(Identifier id) {
        return super.get(id.toString());
    }

    public boolean containsKey(Identifier id) {
        return super.containsKey(id.toString());
    }

    public void putAll(List<Question> questions) {
        for (Question question : questions) {
            put(question.getId(), question.getType());
        }
    }
}
