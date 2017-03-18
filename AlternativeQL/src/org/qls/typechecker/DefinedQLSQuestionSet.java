package org.qls.typechecker;

import org.ql.ast.Identifier;

import java.util.HashSet;

public class DefinedQLSQuestionSet {
    private final HashSet<String> definedQLQuestions = new HashSet<>();

    public boolean declare(Identifier id) {
        return definedQLQuestions.add(id.toString());
    }

    public boolean isDeclared(Identifier id) {
        return definedQLQuestions.contains(id.toString());
    }

    public int size() {
        return definedQLQuestions.size();
    }
}
