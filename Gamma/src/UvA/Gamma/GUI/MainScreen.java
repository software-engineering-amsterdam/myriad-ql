package UvA.Gamma.GUI;

import UvA.Gamma.AST.Form;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by Tjarco, 13-02-17.
 */
public class MainScreen {
    private Form form;


    public MainScreen(Form form) {
        this.form = form;
    }

    public void initUI(Stage stage) throws Exception {
        Text sceneTitle = new Text(form.getId());
        GridPane grid = new GridPane();


        setGridPaneLayout(grid);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        UIVisitor uiVisitor = new UIVisitor(grid, new DefaultWidgetBuilder(form), stage);
        form.accept(uiVisitor);

        stage.setScene(new Scene(grid));
        stage.sizeToScene();
        stage.show();
    }

    private void setGridPaneLayout(GridPane grid) {
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.LEFT);
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().addAll(col1, col2);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }
}

