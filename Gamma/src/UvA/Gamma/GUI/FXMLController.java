package UvA.Gamma.GUI;

import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.Question;
import UvA.Gamma.Validation.TypeChecker;
import UvA.Gamma.Visitors.UIVisitor;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FXMLController {
    private Form form;
    private TypeChecker checker = new TypeChecker();
    private Stage stage;

    private final double VERTICAL_GAP = 10;

    private GridPane grid;
    private GridPane rootGrid;

    public FXMLController(Form form) {
        this.form = form;
    }

    public void initVisitor(GridPane grid) {
        UIVisitor uivis = new UIVisitor(grid, new DefaultWidgetBuilder(form));
        form.forEach(formItem -> formItem.accept(uivis));
    }

//    private int getRowCount(GridPane pane) {
//        int numRows = pane.getRowConstraints().size();
//        for (int i = 0; i < pane.getChildren().size(); i++) {
//            Node child = pane.getChildren().get(i);
//            if (child.isManaged()) {
//                Integer rowIndex = GridPane.getRowIndex(child);
//                if (rowIndex != null) {
//                    numRows = Math.max(numRows, rowIndex + 1);
//                }
//            }
//        }
//        return numRows;
//    }

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