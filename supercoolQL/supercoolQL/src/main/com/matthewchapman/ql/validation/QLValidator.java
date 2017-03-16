package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.gui.errors.ErrorDialogGenerator;
import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.validation.structure.QLStructureChecker;
import com.matthewchapman.ql.validation.type.QLTypeChecker;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Type checker for the QL AST.
 */
public class QLValidator {

    private final QuestionCollection questionCollection;
    private final QLTypeChecker qlTypeChecker;
    private final QLStructureChecker qlStructureChecker;
    private final ErrorDialogGenerator dialogGenerator;

    public QLValidator() {
        this.questionCollection = new QuestionCollection();
        this.qlTypeChecker = new QLTypeChecker();
        this.qlStructureChecker = new QLStructureChecker();
        this.dialogGenerator = new ErrorDialogGenerator();
    }

    public boolean runChecks(Form astRoot) {

        QLErrorLogger mainLogger = new QLErrorLogger();

        //duplicate questions are ok at this point, continue to check the QL
        QLErrorLogger duplicateLog = questionCollection.gatherQuestions(astRoot);
        mainLogger.addMultipleErrors(duplicateLog);

        QLErrorLogger structureLog = qlStructureChecker.checkQLStructure(astRoot, questionCollection.getTypeTable());
        if(structureLog.getErrorNumber() > 0) {
            mainLogger.addMultipleErrors(structureLog);
        }

        //if we have any errors at all at this point, halt.
        if (mainLogger.getErrorNumber() > 0) {
            dialogGenerator.generateErrorBox(mainLogger, "Interpreter Errors Found", "QL encountered an interpreter error", "");
            return false;
        }

        //if we continued due to no errors, halt here if we have some
        QLErrorLogger typeLog = qlTypeChecker.checkExpressionTypes(astRoot, questionCollection.getTypeTable());
        if(typeLog.getErrorNumber() > 0) {
            dialogGenerator.generateErrorBox(typeLog,"Interpreter Errors Found", "QL encountered an interpreter error", "");
            return false;
        }

        return true;
    }
}
