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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ql.ast.types.BooleanType;
import ql.ast.types.NumType;
import ql.ast.types.StringType;
import ql.ast.types.Type;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

@Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(addForm("Form")));
        addForm("Form2");
        primaryStage.show();
    }

    public VBox addForm(String title) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text textTitle = new Text(title);
        textTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(textTitle);

        BooleanType bool = new BooleanType();
        addQuestion(vbox, "Did you sell a house in 2010?", bool, null);
        NumType num = new NumType();
        addQuestion(vbox, "Did you sell a house in 20112?", num, null);

        return vbox;
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
                    System.out.println("Radio button clicked");
                }

            }
        });

        vbox.getChildren().add(new HBox(rbYes, rbNo));
    }

    public void addQuestion(VBox vbox, String question, NumType type, String variableName) {
        ObjectProperty<String> objectProperty = new SimpleObjectProperty<>();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        textField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                objectProperty.set(newValue);
            }
        });


        textField2.textProperty().bind(objectProperty);

        objectProperty.set("test");
        vbox.getChildren().addAll(new Text(question), textField1);
        vbox.getChildren().addAll(new Text(question), textField2);
    }
    public void addQuestion(VBox vbox, String question, StringType type, String variableName) {
        vbox.getChildren().addAll(new Text(question), new TextField());
    }
}