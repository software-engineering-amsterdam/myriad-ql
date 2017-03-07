package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.expression.binary.BinaryOperation;

/**
 * Created by matt on 27/02/2017.
 *
 * Type checker for the QL AST.
 */
public class QLTreeChecker {

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

        //look at each "calculated" question
            //get the expressions and leaf nodes
                //check validity of types
    }

    public void checkTypeCompatability(BinaryOperation op) {

    }

}
