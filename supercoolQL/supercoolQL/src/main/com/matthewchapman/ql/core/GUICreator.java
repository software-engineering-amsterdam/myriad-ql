package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.gui.FormWindow;
import com.matthewchapman.ql.validation.structure.LabelChecker;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by matt on 15/03/2017.
 */
class GUICreator {

    void generateFormUI(Stage stage, Form ast) {

        LabelChecker labelChecker = new LabelChecker();
        labelChecker.gatherQuestions(ast);
        FormWindow window = new FormWindow().makeForm(labelChecker.getQuestionList());

        BorderPane pane = new BorderPane();
        pane.setCenter(window);

        Scene scene = new Scene(pane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
