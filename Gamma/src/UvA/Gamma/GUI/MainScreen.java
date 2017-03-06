package UvA.Gamma.GUI;

import UvA.Gamma.AST.*;
import UvA.Gamma.GUI.FXMLExampleController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * Created by Tjarco, 13-02-17.
 */
public class MainScreen {
    private Parent root;
    private Form form;

    public MainScreen(Form form) {
        this.form = form;


    }


    public void initUI(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml_example.fxml"));
        root = loader.load();
        FXMLExampleController controller = loader.getController();
        System.out.println(controller);
        controller.addFormItem(form);
        stage.setTitle("FXML Welcome");
        stage.setScene(new Scene(root, 300, 275));
        stage.sizeToScene();
        stage.show();
    }
}

