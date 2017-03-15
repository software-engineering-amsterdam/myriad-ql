package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.statement.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Type checker for the QL AST.
 */
public class QLValidator {

    private List<Question> questionList;
    public Map<String, Type> typeTable;
    private final Form astRoot;
    private final QuestionCollection questionCollection;
    private final QLTypeChecker qlTypeChecker;
    private final QLStructureChecker qlStructureChecker;

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

        //qlTypeChecker.checkExpressionTypes(astRoot, questionCollection.getTypeTable());

        qlStructureChecker.checkQLStructure(astRoot, questionCollection.getTypeTable());

    }

}
