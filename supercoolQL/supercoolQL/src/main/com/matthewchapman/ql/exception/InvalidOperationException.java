package com.matthewchapman.ql.exception;

import com.matthewchapman.ql.environment.values.Value;
import javafx.scene.control.Alert;

/**
 * Created by matt on 21/03/2017.
 */
public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException(Value value) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Operation error");
        alert.setHeaderText("Illegal operation detected");
        alert.setContentText("Value" + value.getValue() + "cannot be used in this calculation.");
        alert.showAndWait();
    }

}
