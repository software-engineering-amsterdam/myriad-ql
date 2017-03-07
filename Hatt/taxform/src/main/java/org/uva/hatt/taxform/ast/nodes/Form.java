package org.uva.hatt.taxform.ast.nodes;

import org.uva.hatt.taxform.ast.nodes.items.Item;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Form extends ASTNode {

    private String formId;
    private List<Item> questions;

    public Form(int lineNumber) {
        super(lineNumber);
        this.questions = new ArrayList<>();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public List<Item> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Item> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Form{" +
                "formId='" + formId + '\'' +
                ", questions=" + questions +
                '}';
    }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }

}
