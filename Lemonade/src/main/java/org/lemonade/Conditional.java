package org.lemonade;

import java.util.ArrayList;
import java.util.List;

import org.lemonade.expression.Expression;

public class Conditional extends ASTNode{

    private Expression condition;
    private List<Question> questions;

    //How are we going to validate expressions referring to questions declared above?
    public Conditional (Expression expr, int lineNo) {
        super(lineNo, null);//FIXME temp
        this.condition = expr;
        this.questions = new ArrayList<Question>();
    }
    public List<Question> getQuestions() {
        return questions;
    }

    public Expression getCondition() {
        return condition;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    //Validate the org.lemonade.expression and test whether it can be reduced to a bool.
}
