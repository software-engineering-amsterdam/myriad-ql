package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.FormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.Validation.TypeChecker;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FXMLExampleController {
    private int rowCount;
    private Form form;
    TypeChecker checker = new TypeChecker();

    public void addFormItem(Form form) {
        rootGrid = grid;
        this.form = form;
        for (FormItem item : form.getFormItems()) {
            item.show(this);
        }
    }

    @FXML
    private GridPane grid;
    private GridPane rootGrid;

    @FXML
    public void showQuestion(Question question) {
        assert rootGrid != null;
        Text questionLabel = new Text(question.getQuestion());
        TextField input = new TextField();

        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (question.check(checker, newValue)) {
                input.setStyle("-fx-text-fill: green");
                form.getFormItems().parallelStream().forEach(item -> item.idChanged(form, question, newValue));
            } else {
                input.setStyle("-fx-text-fill: red");
            }
        });
        rootGrid.addRow(++rowCount, questionLabel, input);
    }

    @FXML
    public void showBoolean(Question question) {
        Text questionLabel = new Text(question.getQuestion());
        CheckBox input = new CheckBox();

        input.selectedProperty().addListener((observable, oldValue, newValue) ->
                form.getFormItems().parallelStream().forEach(
                        formItem -> formItem.idChanged(form, question, String.valueOf(newValue))));

        grid.addRow(++rowCount, questionLabel, input);
    }




//    @FXML
//    public void showDateValue(Question question) {
//        Text questionLabel = new Text(question.getQuestion());
//        CheckBox input = new CheckBox();
//        grid.addRow(++rowCount, questionLabel, input);
//    }



    public void showComputed(Computed computed) {
        assert rootGrid != null;
        Text label = new Text(computed.getLabel());
        Text result = new Text(computed.getStringValueProperty().get());

        result.textProperty().bind(computed.getStringValueProperty());

        rootGrid.addRow(++rowCount, label, result);
    }

    public GridPane startRenderCondition() {
        rootGrid = new GridPane();
        return rootGrid;
    }

    public void stopRenderCondition() {
        grid.addRow(++rowCount, rootGrid);
        rootGrid = grid;
    }
}