package ql.view;/**
 * Created by Erik on 20-2-2017.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(addVBox()));
        primaryStage.show();
    }

    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        for (int i = 0; i < 5; i++){
            vbox.getChildren().add(addGrid());
        }

        return vbox;
    }

    public GridPane addGrid() {
        Text question = new Text("Question");
        Text id = new Text("id");
        TextField textField = new TextField ();

        GridPane grid = new GridPane();
        grid.add(id, 0, 0);
        grid.add(question, 0, 1);
        grid.add(textField, 1, 1);

        return grid;
    }
}
