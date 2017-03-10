package org.ql;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.gui.GUIHandler;
import org.ql.gui.MainStage;
import org.ql.parser.Parser;
import org.ql.typechecker.TypeChecker;
import org.ql.typechecker.issues.IssuesStorage;

public class Main extends Application {
    private final String QL_SOURCE_PATH = "drafts/ExampleForm.aql";

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileLoader fileLoader = new FileLoader();
        String qlSource = fileLoader.getSourceFromResource(QL_SOURCE_PATH);

        Form form = runParser(qlSource);

        if (hasFormTypeErrors(form)) {
            System.out.println("TypeChecker: An issue was found!");
        } else {
            runGUI(primaryStage, form);
        }
    }

    // TODO: Get exampleForm file from the project.
    public Form runParser(String src) {
        return new Parser().parseForm(src);
    }

    public boolean hasFormTypeErrors(Form form) {
        TypeChecker typeChecker = new TypeChecker();
        IssuesStorage issues = typeChecker.checkForm(form);

        return issues.hasErrors();
    }

    public void runGUI(Stage primaryStage, Form form) {
        primaryStage.show();
        GUIHandler guiHandler = new GUIHandler(new MainStage(primaryStage, form.getName().toString()), form);
        guiHandler.runGUI();
    }
}
