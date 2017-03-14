package org.ql;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.gui.GUIHandler;
import org.ql.gui.Window;
import org.ql.parser.Parser;
import org.ql.typechecker.TypeChecker;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;

public class Main extends Application {
    private final String QL_SOURCE_PATH = "drafts/ExampleForm.aql";

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Form form = runParser(getSourceFile(QL_SOURCE_PATH));

        IssuesStorage issueStorage = getTypeCheckerIssues(form);
        if (issueStorage.hasErrors()) {
            issueStorage.printIssues();
        } else {
            runGUI(primaryStage, form);
        }
    }

    private String getSourceFile(String sourceFile) {
        return new FileLoader().getSourceFromResource(sourceFile);
    }

    private Form runParser(String src) {
        return new Parser().parseForm(src);
    }

    private IssuesStorage getTypeCheckerIssues(Form form) {
        return new TypeChecker().checkForm(form);
    }

    private void runGUI(Stage primaryStage, Form form) {
        primaryStage.show();
        GUIHandler guiHandler = new GUIHandler(new Window(primaryStage, form.getName().toString()), form);
        guiHandler.runGUI();
    }
}
