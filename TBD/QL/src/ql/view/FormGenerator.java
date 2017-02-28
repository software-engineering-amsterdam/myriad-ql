package ql.view;


import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ql.ast.types.*;
import ql.ast.visistor.environment.Environment;

/**
 * Created by Erik on 20-2-2017.
 */
public class FormGenerator {
    private final Environment environment;

    public FormGenerator(Environment environment) {
        this.environment = environment;
    }

    public Scene addForm(String title) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text textTitle = new Text(title);
        textTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(textTitle);

        BooleanType bool = new BooleanType();
        addQuestion(vbox, "Did you sell a house in 2010?", bool, null);
        IntType num = new IntType();
        addQuestion(vbox, "Did you sell a house in 20112?", num, null);

        return new Scene(vbox);
    }

    public void addQuestion(VBox vbox, String question, BooleanType type, String variableName) {
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

        vbox.getChildren().add(new HBox(rbYes, rbNo));
    }

    public void addQuestion(VBox vbox, String question, IntType type, String variableName) {
        TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });
        vbox.getChildren().addAll(new Text(question), textField);
    }

    public void addQuestion(VBox vbox, String question, FloatType type, String variableName) {
        TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });
        vbox.getChildren().addAll(new Text(question), textField);
    }

    public void addQuestion(VBox vbox, String question, StringType type, String variableName) {
        vbox.getChildren().addAll(new Text(question), new TextField());
    }
}
