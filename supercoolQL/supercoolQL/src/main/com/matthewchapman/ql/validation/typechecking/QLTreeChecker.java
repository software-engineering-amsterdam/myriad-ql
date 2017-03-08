package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

/**
 * Created by matt on 27/02/2017.
 *
 * Type checker for the QL AST.
 */
public class QLTreeChecker extends AbstractQLVisitor<Void> {

    private Form astRoot;
    private QuestionCollection questionCollection;
    private ExpressionChecker expressionChecker;

    public QLTreeChecker(Form form) {
        this.astRoot = form;
        this.questionCollection = new QuestionCollection();
        this.expressionChecker = new ExpressionChecker();
    }

    public void runChecks() {

        // gather all of the questions into one place
        questionCollection.gatherQuestions(astRoot);
        //find duplicates
        questionCollection.findDuplicates();
        checkTypeCompatability();

    }

    public void checkTypeCompatability() {
        for(Question question:questionCollection.getQuestionList()) {
            question.accept(this);
        }
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion) {
        calculatedQuestion.getCalculation().accept(expressionChecker);
        return null;
    }
}
