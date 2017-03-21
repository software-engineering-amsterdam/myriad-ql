package org.qls.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.form.Form;
import org.ql.gui.Window;
import org.qls.ast.StyleSheet;

public class QLSApplication extends Application {
    private static Form form;
    private static StyleSheet styleSheet;

    @Override
    public void start(Stage primaryStage) throws Exception {
        QLSGUIFormBuilder guiFormBuilder = new QLSGUIFormBuilder(new Window(primaryStage, form.getName().toString()), form, styleSheet);
        guiFormBuilder.constructGUIForm();
        primaryStage.show();
    }

    public static void run(Form formAST, StyleSheet styleSheetAST) {
        form = formAST;
        styleSheet = styleSheetAST;
        launch();
    }
}
