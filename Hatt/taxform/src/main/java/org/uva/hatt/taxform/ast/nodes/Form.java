package org.uva.hatt.taxform.ast.nodes;

import org.uva.hatt.taxform.ast.nodes.items.Item;

import java.util.List;

public class Form extends ASTNode {

    private final String formId;
    private final List<Item> questions;

    public Form(int lineNumber, String formId, List<Item> questions) {
        super(lineNumber);
        this.formId = formId;
        this.questions = questions;
    }

    public String getFormId() {
        return formId;
    }

    public List<Item> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Form{" +
                "formId='" + formId + '\'' +
                ", questions=" + questions +
                '}';
    }

    public <T> T accept(FormVisitor<T> visitor){
        return visitor.visit(this);
    }

}
