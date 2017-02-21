package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.FormItem;
import UvA.Gamma.AST.Question;
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

        //TODO: Bind listener to update UI based on new value


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
