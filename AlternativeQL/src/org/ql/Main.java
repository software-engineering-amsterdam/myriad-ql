package org.ql;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.evaluator.Evaluator;
import org.ql.evaluator.ValueTable;
import org.ql.gui.GUIEval;
import org.ql.gui.GUIHandler;
import org.ql.gui.elements.QuestionElementBuilder;
import org.ql.parser.Parser;
import org.ql.typechecker.TypeChecker;

public class Main extends Application {
    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Form form = runParser();

        if(hasFormTypeErrors(form)) {
            System.out.println("TypeChecker: An issue was found!");
        } else {
            runEvaluator(form);
            runGUI(primaryStage, form);
        }
    }

    // TODO: Get exampleForm file from the project.
    public Form runParser() {
        Parser parser = new Parser();
        Form form = parser.parseForm(
                "form TestForm {" +
                        "boolean hasSoldHouse: \"Did you sell a house in 2010?\" = (false&&true);" +
                        "if (hasSoldHouse) {" +
                        "boolean sellingPrice: \"What was the selling price?\" = true;" +
                        "}" +
                        "}");

        return form;
    }

    public boolean hasFormTypeErrors(Form form) {
        TypeChecker typeChecker = new TypeChecker(form);
        typeChecker.checkForm();

        return typeChecker.hasErrors();
    }

    public void runEvaluator(Form form) {
        ValueTable valueTable = new ValueTable();
        Evaluator evaluator = new Evaluator(valueTable);
        evaluator.visitForm(form, null);
    }

    public void runGUI(Stage primaryStage, Form form) {
        GUIHandler guiHandler = new GUIHandler(primaryStage, form);
    }
}
