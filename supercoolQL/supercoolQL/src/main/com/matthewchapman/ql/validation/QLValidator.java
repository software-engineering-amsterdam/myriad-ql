package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.statement.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by matt on 27/02/2017.
 *
 * Type checker for the QL AST.
 */
public class QLValidator {

    private Form astRoot;
    private QuestionCollection questionCollection;
    private QLTypeChecker qlTypeChecker;
    private QLStructureChecker qlStructureChecker;

    public List<Question> questionList;
    public Map<String, Type> typeTable;

    public QLValidator(Form form) {
        this.astRoot = form;
        this.questionCollection = new QuestionCollection();
        this.qlTypeChecker = new QLTypeChecker();
        this.qlStructureChecker = new QLStructureChecker();
    }

    public void runChecks() {

        questionCollection.gatherQuestions(astRoot);
        questionList = questionCollection.getQuestionList();
        questionCollection.findDuplicates();

        //qlTypeChecker.checkExpressions(astRoot, questionCollection.getTypeTable());

        qlStructureChecker.checkForCircularReferences(astRoot, questionCollection.getTypeTable());

    }

}
