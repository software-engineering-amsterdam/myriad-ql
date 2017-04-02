package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.errorhandling.ErrorLogger;
import com.matthewchapman.ql.gui.errors.ErrorDialogGenerator;
import com.matthewchapman.ql.validation.structure.ExpressionChecker;
import com.matthewchapman.ql.validation.structure.LabelChecker;
import com.matthewchapman.ql.validation.structure.cyclic.DependencyChecker;
import com.matthewchapman.ql.validation.type.TypeChecker;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Type checker for the QL AST.
 */
public class Validator {

    private static final String INTERPRETER_ERROR_TITLE = "Interpreter Errors Found";
    private static final String INTERPRETER_ERROR_BODY = "QL encountered an interpreter error";
    private static final String INTERPRETER_WARNING_TITLE = "Interpreter Warnings Found";
    private static final String INTERPRETER_WARNING_BODY = "QL encountered an interpreter warning";
    private final LabelChecker labelChecker;
    private final TypeChecker typeChecker;
    private final DependencyChecker dependencyChecker;
    private final ExpressionChecker expressionChecker;

    public Validator() {
        this.labelChecker = new LabelChecker();
        this.typeChecker = new TypeChecker();
        this.dependencyChecker = new DependencyChecker();
        this.expressionChecker = new ExpressionChecker();
    }

    public boolean runChecks(Form astRoot) {

        ErrorLogger mainLogger = new ErrorLogger();

        //duplicate questions are ok at this point, continue to check the QL
        ErrorLogger duplicateLog = labelChecker.gatherQuestions(astRoot);
        mainLogger.addMultipleErrors(duplicateLog);
        mainLogger.addMultipleWarnings(duplicateLog);

        //output any warnings we have
        if (mainLogger.getWarningNumber() > 0) {
            ErrorDialogGenerator.generateWarningListBox(mainLogger.getWarningsAsString(), INTERPRETER_WARNING_TITLE, INTERPRETER_WARNING_BODY);
        }

        //missing parameters are bad
        ErrorLogger parameterLog = expressionChecker.checkExpressions(astRoot, labelChecker.getTypeTable());
        mainLogger.addMultipleErrors(parameterLog);

        //if we have any errors at all at this point, halt.
        if (mainLogger.getErrorNumber() > 0) {
            ErrorDialogGenerator.generateErrorListBox(mainLogger.getErrorsAsString(), INTERPRETER_ERROR_TITLE, INTERPRETER_ERROR_BODY);
            return false;
        }

        //circular dependencies are bad
        ErrorLogger dependencyLog = dependencyChecker.checkForCircularDependencies(expressionChecker.getExpressionMap());
        if (dependencyLog.getErrorNumber() > 0) {
            mainLogger.addMultipleErrors(dependencyLog);
            ErrorDialogGenerator.generateErrorListBox(mainLogger.getErrorsAsString(), INTERPRETER_ERROR_TITLE, INTERPRETER_ERROR_BODY);
            return false;
        }

        //incorrect types are also bad
        ErrorLogger typeLog = typeChecker.checkExpressionTypes(astRoot, labelChecker.getTypeTable());
        if (typeLog.getErrorNumber() > 0) {
            ErrorDialogGenerator.generateErrorListBox(typeLog.getErrorsAsString(), INTERPRETER_ERROR_TITLE, INTERPRETER_ERROR_BODY);
            return false;
        }

        return true;
    }
}
