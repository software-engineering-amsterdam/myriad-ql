package com.matthewchapman.ql.core;

import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.gui.FormWindow;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by matt on 15/03/2017.
 */
class GUICreator {

    void generateFormUI(Stage stage, FormEnvironment env) {

        FormWindow window = new FormWindow().makeForm(env.getQuestionsAsList());

        BorderPane pane = new BorderPane();
        pane.setCenter(window);

        Scene scene = new Scene(pane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
