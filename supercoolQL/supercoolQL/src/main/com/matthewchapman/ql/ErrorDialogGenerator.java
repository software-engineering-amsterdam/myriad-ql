package com.matthewchapman.ql;

import com.matthewchapman.ql.QLErrorLogger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by matt on 15/03/2017.
 */
public class ErrorDialogGenerator {

    public void generateErrorBox(QLErrorLogger logger) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errors Found");
        alert.setHeaderText("Errors were found when processing the QL file.");
        alert.setContentText("Fix the following to continue:");

        String exceptionText = logger.toString();
        Label label = new Label("Resolve the following to continue:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setContent(expContent);

        alert.showAndWait();
        Platform.exit();
    }
}
