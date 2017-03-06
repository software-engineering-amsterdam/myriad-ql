package org.ql.collection;

import org.ql.ast.Form;
import org.ql.ast.statement.Question;
import org.ql.collection.visitor.QuestionCollectVisitor;
import org.ql.typechecker.SymbolTable;

public class QuestionStorage {
    private final QuestionCollectVisitor questionCollectVisitor;

    public QuestionStorage() {
        questionCollectVisitor = new QuestionCollectVisitor();
    }

    public Questions collectQuestions(Form form) {
        return questionCollectVisitor.collect(form);
    }

    public SymbolTable createSymbolTable(Form form) {
        SymbolTable symbolTable = new SymbolTable();

        for (Question question : this.collectQuestions(form)) {
            symbolTable.declare(question.getId(), question.getType());
        }

        return symbolTable;
    }
}
