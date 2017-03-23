package org.ql.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.form.Form;

public class QLApplication extends Application {

    private static Form form;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIFormBuilder guiFormBuilder = new GUIFormBuilder(new Window(primaryStage, form.getName().toString()), form);
        guiFormBuilder.constructGUIForm();
        primaryStage.show();
    }

    public static void run(Form formAST) {
        form = formAST;
        launch();
    }
}
