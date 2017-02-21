package UvA.Gamma.GUI;

import UvA.Gamma.AST.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by Tjarco, 13-02-17.
 */
public class MainScreen {
    private GridPane root;
    private Stage stage;
    private int rowCount;
    private Form form;

    public MainScreen(Form form) {
        this.form = form;
    }

    public void initUI(Stage stage) {
        root = new GridPane();
        root.setHgap(15);
        root.setVgap(8);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 200);

        stage.setTitle("Grid try");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        this.stage = stage;
    }

    public void addFormItem(FormItem item) {
        item.show(this);
    }

    public void showQuestion(Question question) {
        Text questionLabel = new Text(question.getQuestion());
        TextField input = new TextField();
        input.textProperty().bindBidirectional(question.getStringValueProperty());

        input.textProperty().addListener((observable, oldValue, newValue) ->
                form.getFormItems().forEach(item -> item.idChanged(question.getId(), newValue))
        );

        root.addRow(++rowCount, questionLabel, input);
        stage.sizeToScene();
    }

    public void showComputed(Computed computed) {
        Text label = new Text(computed.getLabel());
        Text result = new Text(computed.getStringValueProperty().get());

        //Bind the value of the evaluated expression to the label
        result.textProperty().bind(computed.getStringValueProperty());

        root.addRow(++rowCount, label, result);
        stage.sizeToScene();
    }

    public void showCondtion(Condition condition) {

    }
}
