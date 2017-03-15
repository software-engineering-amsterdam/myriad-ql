package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.QLErrorLogger;
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

        QLErrorLogger duplicateLog = questionCollection.gatherQuestions(astRoot);

        if(duplicateLog.getErrorNumber() > 0) {
            duplicateLog.printErrors();
        }

        QLErrorLogger structureLog = qlStructureChecker.checkQLStructure(astRoot, questionCollection.getTypeTable());

        if(structureLog.getErrorNumber() > 0) {
            structureLog.printErrors();
        } else {
            QLErrorLogger typeLog = qlTypeChecker.checkExpressionTypes(astRoot, questionCollection.getTypeTable());

            if(typeLog.getErrorNumber() > 0) {
                typeLog.printErrors();
            }
        }


    }

}
