package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.core.QLErrorLogger;
import com.matthewchapman.ql.gui.errors.ErrorDialogGenerator;
import com.matthewchapman.ql.validation.structure.cyclic.QLDependencyChecker;
import com.matthewchapman.ql.validation.structure.QLExpressionChecker;
import com.matthewchapman.ql.validation.structure.QuestionCollection;
import com.matthewchapman.ql.validation.type.QLTypeChecker;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Type checker for the QL AST.
 */
public class QLValidator {

    public static final String INTERPRETER_ERROR_TITLE = "Interpreter Errors Found";
    public static final String INTERPRETER_ERROR_BODY = "QL encountered an interpreter error";
    private final QuestionCollection questionCollection;
    private final QLTypeChecker qlTypeChecker;
    private final QLDependencyChecker qlDependencyChecker;
    private final ErrorDialogGenerator dialogGenerator;
    private final QLExpressionChecker qlExpressionChecker;

    public QLValidator() {
        this.dialogGenerator = new ErrorDialogGenerator();
        this.questionCollection = new QuestionCollection();
        this.qlTypeChecker = new QLTypeChecker();
        this.qlDependencyChecker = new QLDependencyChecker();
        this.qlExpressionChecker = new QLExpressionChecker();
    }

    public boolean runChecks(Form astRoot) {

        QLErrorLogger mainLogger = new QLErrorLogger();

        //duplicate questions are ok at this point, continue to check the QL
        QLErrorLogger duplicateLog = questionCollection.gatherQuestions(astRoot);
        mainLogger.addMultipleErrors(duplicateLog);

        //missing parameters are bad
        QLErrorLogger parameterLog = qlExpressionChecker.checkExpressions(astRoot, questionCollection.getTypeTable());
        mainLogger.addMultipleErrors(parameterLog);

        //if we have any errors at all at this point, halt.
        if (mainLogger.getErrorNumber() > 0) {
            dialogGenerator.generateErrorBox(mainLogger, INTERPRETER_ERROR_TITLE, INTERPRETER_ERROR_BODY);
            return false;
        }

        //circular dependencies are bad
        QLErrorLogger dependencyLog = qlDependencyChecker.checkForCircularDependencies(questionCollection.getTypeTable(), qlExpressionChecker.getExpressionMap());
        if (dependencyLog.getErrorNumber() > 0) {
            mainLogger.addMultipleErrors(dependencyLog);
            dialogGenerator.generateErrorBox(mainLogger, INTERPRETER_ERROR_TITLE, INTERPRETER_ERROR_BODY);
            return false;
        }

        //incorrect types are also bad
        QLErrorLogger typeLog = qlTypeChecker.checkExpressionTypes(astRoot, questionCollection.getTypeTable());
        if (typeLog.getErrorNumber() > 0) {
            dialogGenerator.generateErrorBox(typeLog, INTERPRETER_ERROR_TITLE, INTERPRETER_ERROR_BODY);
            return false;
        }

        return true;
    }
}
