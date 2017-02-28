package ql.view.elements;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ql.ast.types.NumType;

/**
 * Created by Erik on 28-2-2017.
 */
public class QLQuestionBox extends VBox {

    public QLQuestionBox(String question, NumType type, String variableName) {
        TextField textField1 = new TextField();
        textField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                /*Int regex: "[-+]?[0-9]*"*/
                /*Float regex: "[-+]?[0-9]*(\\.[0-9]*)?"*/
                if (!textField1.getText().matches("[-+]?[0-9]*(\\.[0-9]*)?")) {
                    textField1.setText(oldValue);
                }
            }
        });
        this.getChildren().addAll(new Text(question), textField1);
    }

}
