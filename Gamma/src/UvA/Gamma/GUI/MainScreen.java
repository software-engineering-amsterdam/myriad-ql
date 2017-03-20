package UvA.Gamma.GUI;

import UvA.Gamma.AST.Form;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
        root = loader.load();
        FXMLController controller = loader.getController();
        System.out.println(controller);
        controller.addFormItem(form);
        stage.setTitle("Tax Questionnaire");
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.show();
    }
}

