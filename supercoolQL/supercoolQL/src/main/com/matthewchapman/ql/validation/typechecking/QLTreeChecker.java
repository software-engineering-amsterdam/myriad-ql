package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

import java.util.List;
import java.util.Map;

/**
 * Created by matt on 27/02/2017.
 *
 * Type checker for the QL AST.
 */
public class QLTreeChecker extends AbstractQLVisitor<Void> {

    private Form astRoot;
    private QuestionCollection questionCollection;
    private ExpressionChecker expressionChecker;

    public List<Question> questionList;
    public Map<String, Type> typeTable;

    public QLTreeChecker(Form form) {
        this.astRoot = form;
        this.questionCollection = new QuestionCollection();
        this.expressionChecker = new ExpressionChecker();
    }

    public void runChecks() {

        questionCollection.gatherQuestions(astRoot);

        questionList = questionCollection.getQuestionList();
        typeTable = questionCollection.getTypeTable();

        questionCollection.findDuplicates();
        checkExpressions();

    }

    private void checkExpressions() {
        for(Statement statement:astRoot.getStatements()) {
            expressionChecker.checkExpression(statement, questionCollection.getTypeTable());
        }
    }

}
