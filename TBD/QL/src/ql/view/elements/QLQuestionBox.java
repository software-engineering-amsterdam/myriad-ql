package ql.view.elements;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ql.ast.types.*;

/**
 * Created by Erik on 28-2-2017.
 */
public class QLQuestionBox extends VBox {

    public QLQuestionBox(String question, BooleanType type, String variableName) {
        final ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton rbYes = new RadioButton("Yes");
        RadioButton rbNo = new RadioButton("No");
        rbYes.setToggleGroup(toggleGroup);
        rbNo.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                if (toggleGroup.getSelectedToggle() != null) {
                    System.out.println("Radio button changed");
                }

            }
        });

        this.getChildren().addAll(new Text(question), new HBox(rbYes, rbNo));
    }

    public QLQuestionBox(String question, IntType type, String variableName) {
        TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!textField.getText().matches("[-+]?[0-9]*")) {
                    textField.setText(oldValue);
                }
            }
        });
        this.getChildren().addAll(new Text(question), textField);
    }

    public QLQuestionBox(String question, FloatType type, String variableName) {
        TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                /*Int regex: "[-+]?[0-9]*"*/
                /*Float regex: "[-+]?[0-9]*(\\.[0-9]*)?"*/
                if (!textField.getText().matches("[-+]?[0-9]*(\\\\.[0-9]*)?")) {
                    textField.setText(oldValue);
                }
            }
        });
        this.getChildren().addAll(new Text(question), textField);
    }

    public QLQuestionBox(String question, StringType type, String variableName) {
        this.getChildren().addAll(new Text(question), new TextField());
    }

}
