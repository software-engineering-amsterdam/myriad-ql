package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Question;
import UvA.Gamma.GUI.WidgetBuilder;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.List;

/**
 * Created by casboot on 09-04-17.
 */
public class UIVisitor extends BaseVisitor {
    private GridPane grid;
    private WidgetBuilder builder;

    public UIVisitor(GridPane grid, WidgetBuilder builder) {
        this.grid = grid;
        this.builder = builder;
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
        GridPane gridPane = new GridPane();
        UIVisitor uiVisitor = new UIVisitor(gridPane, builder);
        condition.visitChildNodes(uiVisitor);
        condition.setGridPane(gridPane);
        gridPane.managedProperty().bind(gridPane.visibleProperty());
//        gridPane.visibleProperty().addListener((observable, oldValue, newValue) -> stage.sizeToScene());
        grid.add(gridPane, 0, getRowCount(grid) + 1, 2, 1);
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
