package org.ql.typechecker.expression;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapSymbolTable implements SymbolTable {

    private final Map<String, Type> definitions = new HashMap<>();

    public HashMapSymbolTable(List<Question> questions) {
        putAll(questions);
    }

    public Type put(Identifier id, Type value) {
        return definitions.put(id.toString(), value);
    }

    public Type get(Identifier id) {
        return definitions.get(id.toString());
    }

    public boolean has(Identifier id) {
        return definitions.containsKey(id.toString());
    }

    public void putAll(List<Question> questions) {
        for (Question question : questions) {
            put(question.getId(), question.getType());
        }
    }
}
