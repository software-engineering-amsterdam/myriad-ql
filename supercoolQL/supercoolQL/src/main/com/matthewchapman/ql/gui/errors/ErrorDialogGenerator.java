package com.matthewchapman.ql.gui.errors;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Created by matt on 15/03/2017.
 *
 * Generates GUI error boxes
 */
public final class ErrorDialogGenerator {

    private ErrorDialogGenerator() {

    }

    public static void generateErrorListBox(String content, String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        GridPane expContent = getGridPane(content, "Resolve the following to continue");
        alert.getDialogPane().setContent(expContent);

        alert.showAndWait();
        Platform.exit();
    }

    public static void generateWarningListBox(String content, String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        GridPane expContent = getGridPane(content, "The following non-fatal warnings have occurred");
        alert.getDialogPane().setContent(expContent);

        alert.showAndWait();
    }

    private static GridPane getGridPane(String content, String labelText) {
        Label label = new Label(labelText);

        TextArea textArea = new TextArea(content);
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
        return expContent;
    }
}
