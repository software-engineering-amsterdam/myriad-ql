package org.ql.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.parser.Parser;

public class GUIHandler extends Application {

    private MainStage mainStage;

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parser parser = new Parser(); // TODO: Remove
        Form form = parser.parseForm("form TestForm {}"); // TODO: Remove

        primaryStage.show();
        MainStage mainStage = new MainStage(primaryStage);
        GUIVisitor guiVisitor = new GUIVisitor(mainStage);
        guiVisitor.visit(form);
    }
}
