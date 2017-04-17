package UvA.Gamma.Visitors;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.Value;
import UvA.Gamma.AST.IdentifiableFormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.GUI.WidgetBuilder;
import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    public GridPane getGrid() {
        return grid;
    }

    @Override
    public void visit(Question ques) {
       builder.getWidget(ques);
        Text questionLabel = new Text(ques.getLabel());
        TextField input = new TextField();
        grid.addRow(getRowCount(grid) + 1, questionLabel, input);
    }

//    @Override
//    public void visit(Condition con) {
//
//
//    }

    @Override
    public void visit(Computed com) {
        builder.getWidget(com);
        Text label = new Text(com.getLabel());
        Text result = new Text(com.getStringValueProperty().get());

        result.textProperty().bind(com.getStringValueProperty());

        grid.addRow(getRowCount(grid) + 1, label, result);
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

}

//    In de visitor zelf vraag je elke keer dat je een widget nodig hebt dit aan het interface WidgetBuilder
//    bijvoorbeeld:
//    visit(Computed c){ WidgetBuilder.getWidget(Computed c); }
//    oid
//    of showQuestion() kan inderdaad ook, maar dat impliceert dat die builder ook daadwerkelijk de widget gaat tonen, misschien is getWidget wel beter