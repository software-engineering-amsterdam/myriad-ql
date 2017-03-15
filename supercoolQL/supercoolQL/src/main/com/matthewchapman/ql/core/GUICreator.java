package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Created by matt on 15/03/2017.
 */
class GUICreator {

    void generateFormUI(Stage stage, Form ast) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hooray!");
        alert.setHeaderText("It works!");
        alert.setContentText("Form found with title " + ast.getName());
        alert.showAndWait();
    }
}
