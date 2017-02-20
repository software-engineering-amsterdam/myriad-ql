package org.uva.hatt.taxform.ast.nodes;

import java.util.ArrayList;
import java.util.List;

public class Form extends ASTNode{

    private String formId;
    private List<Question> questions;

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Form{" +
                "formId='" + formId + '\'' +
                ", questions=" + questions +
                '}';
    }
}
