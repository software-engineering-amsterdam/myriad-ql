package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.FormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Values.Money;
import UvA.Gamma.Validation.TypeChecker;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Stack;

public class FXMLController {
    private Form form;
    private TypeChecker checker = new TypeChecker();
    private Stage stage;

    private final double VERTICAL_GAP = 10;

    void addFormItems(Form form) {
        rootGrid = grid;
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.LEFT);
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().addAll(col1, col2);

        conditionStack = new Stack<>();
        conditionStack.push(rootGrid);
        this.form = form;
        for (FormItem item : form) {
            item.show(this);
        }
    }

    @FXML
    private GridPane grid;
    private GridPane rootGrid;
    private Stack<GridPane> conditionStack;

    public void showQuestion(Question question) {
        assert rootGrid != null;
        Text questionLabel = new Text(question.getQuestion());
        TextField input = new TextField();

        questionOnUpdate(question, input);
        rootGrid.addRow(getRowCount(rootGrid) + 1, questionLabel, input);
    }

    public void showBoolean(Question question) {
        assert rootGrid != null;
        Text questionLabel = new Text(question.getQuestion());
        CheckBox input = new CheckBox();

        input.selectedProperty().addListener((observable, oldValue, newValue) ->
                form.stream().forEach(
                        formItem -> formItem.idChanged(form, question, String.valueOf(newValue))));

        rootGrid.addRow(getRowCount(rootGrid) + 1, questionLabel, input);
    }

    public void showDateValue(Question question) {
        assert rootGrid != null;
        Text questionLabel = new Text(question.getQuestion());
        final DatePicker datePicker = new DatePicker();

        datePicker.setOnAction(t -> {
            LocalDate date = datePicker.getValue();
            System.err.println("Selected date: " + date);
        });
        rootGrid.addRow(getRowCount(rootGrid) + 1, questionLabel, datePicker);
    }


    public void showComputed(Computed computed) {
        assert rootGrid != null;
        Text label = new Text(computed.getLabel());
        Text result = new Text(computed.getStringValueProperty().get());

        result.textProperty().bind(computed.getStringValueProperty());

        rootGrid.addRow(getRowCount(rootGrid) + 1, label, result);
    }

    public void showMoney(Question question) {
        assert rootGrid != null;
        Text questionLabel = new Text(question.getQuestion());

        Text euroLabel = new Text(Money.CURRENCY_SYMBOL);
        TextField input = new TextField();
        questionOnUpdate(question, input);

        HBox box = new HBox();
        box.getChildren().addAll(euroLabel, input);
        box.setAlignment(Pos.CENTER_RIGHT);

        rootGrid.addRow(getRowCount(rootGrid) + 1, questionLabel, box);
    }

    /*
        To render a condition, we create a new Gridpane to add the children of the condtion to
     */
    public GridPane startRenderCondition() {
        rootGrid = new GridPane();
        rootGrid.getColumnConstraints().addAll(grid.getColumnConstraints());
        rootGrid.setVgap(VERTICAL_GAP);

        conditionStack.push(rootGrid);
        rootGrid.managedProperty().bind(rootGrid.visibleProperty());
        return rootGrid;
    }

    /*
        This method is called when all children of the Condition have been added. The Condition Gridpane is added to
        the current root.
     */
    public void stopRenderCondition() {
        GridPane pane = conditionStack.pop();
        rootGrid = conditionStack.peek();
        rootGrid.add(pane, 0, getRowCount(rootGrid) + 1, 2, 1);
    }

    private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if (rowIndex != null) {
                    numRows = Math.max(numRows, rowIndex + 1);
                }
            }
        }
        return numRows;
    }

    private void questionOnUpdate(Question question, TextField input) {
        assert form != null;
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                form.stream().forEach(item -> item.idChanged(form, question, newValue));
            }
            if (question.check(checker, newValue)) {
                input.setStyle("-fx-text-fill: green");
                form.stream().forEach(item -> item.idChanged(form, question, newValue));
            } else {
                input.setStyle("-fx-text-fill: red");
            }
        });
    }
}