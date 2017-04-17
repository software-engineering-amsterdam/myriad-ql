package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Question;
import UvA.Gamma.GUI.WidgetBuilder;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.util.List;

/**
 * Created by casboot on 09-04-17.
 */
public class UIVisitor extends BaseVisitor {
    private GridPane grid;
    private WidgetBuilder builder;
    private Stage stage;

    public UIVisitor(GridPane grid, WidgetBuilder builder, Stage stage) {
        this.grid = grid;
        this.builder = builder;
        this.stage = stage;
    }

    @Override
    public void visit(Question ques) {
        placeWidget(builder.getWidget(ques));
    }

    @Override
    public void visit(Computed com) {
        placeWidget(builder.getWidget(com));
    }

    @Override
    public void visit(Condition condition) {
        GridPane thenPane = new GridPane();
        GridPane elsePane = new GridPane();
        UIVisitor thenVisitor = new UIVisitor(thenPane, builder, stage);
        UIVisitor elseVisitor = new UIVisitor(elsePane, builder, stage);
        condition.visitThenChildNodes(thenVisitor);
        condition.visitElseChildNodes(elseVisitor);
        condition.setThenPane(thenPane);
        condition.setElsePane(elsePane);
        thenPane.managedProperty().bind(thenPane.visibleProperty());
        grid.add(thenPane, 0, getRowCount(grid) + 1, 2, 1);
        elsePane.managedProperty().bind(elsePane.visibleProperty());
        grid.add(elsePane, 0, getRowCount(grid) + 1, 2, 1);
        thenPane.visibleProperty().addListener((observable, oldValue, newValue) -> stage.sizeToScene());
        elsePane.visibleProperty().addListener((observable, oldValue, newValue) -> stage.sizeToScene());
    }

    private void placeWidget(List<Node> widgets) {
        int row = getRowCount(grid) + 1;
        int column = 0;
        for (Node widget : widgets) {
            grid.add(widget, column, row);
            column++;
        }
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

    @Override
    public boolean shouldTraverseRecursive() {
        return false;
    }
}
