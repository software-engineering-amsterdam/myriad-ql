package UvA.Gamma.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * Created by Tjarco on 13-02-17.
 */
public class MainScreen{

    public void initUI(Stage stage){

        GridPane root = new GridPane();
        root.setHgap(8);
        root.setVgap(8);
        root.setPadding(new Insets(5));


        for(int i = 0; i<5; i++) {
            Label label = new Label("Name: ");
            TextField textField = new TextField();
            Button okButton = new Button("Ok");
            okButton.setOnAction((ActionEvent event) -> {
                label.setText(textField.getText());
            });

            root.add(label, 0, i);
            root.add(textField, 1, i);
            root.add(okButton, 2, i);
        }

        Scene scene = new Scene(root, 280, 200);

        stage.setTitle("Grid try");
        stage.setScene(scene);
        stage.show();
    }


}
