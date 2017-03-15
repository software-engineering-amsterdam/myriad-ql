package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ErrorDialogGenerator;
import com.matthewchapman.ql.QLErrorLogger;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.statement.Question;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

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
    private final ErrorDialogGenerator dialogGenerator;

    public QLValidator(Form form) {
        this.astRoot = form;
        this.questionCollection = new QuestionCollection();
        this.qlTypeChecker = new QLTypeChecker();
        this.qlStructureChecker = new QLStructureChecker();
        this.dialogGenerator = new ErrorDialogGenerator();
    }

    public boolean runChecks() {

        QLErrorLogger mainLogger = new QLErrorLogger();

        QLErrorLogger duplicateLog = questionCollection.gatherQuestions(astRoot);
        mainLogger.addMultipleErrors(duplicateLog);

        QLErrorLogger structureLog = qlStructureChecker.checkQLStructure(astRoot, questionCollection.getTypeTable());

        if (structureLog.getErrorNumber() > 0) {
            mainLogger.addMultipleErrors(structureLog);
            dialogGenerator.generateErrorBox(mainLogger);
            return false;
        } else {
            QLErrorLogger typeLog = qlTypeChecker.checkExpressionTypes(astRoot, questionCollection.getTypeTable());

            if (typeLog.getErrorNumber() > 0) {
                dialogGenerator.generateErrorBox(typeLog);
                return false;
            }

            if (mainLogger.getErrorNumber() > 0) {
                dialogGenerator.generateErrorBox(mainLogger);
                return false;
            } else {
                return true;
            }
        }
    }
}
