package UvA.Gamma.GUI;

import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.Question;
import UvA.Gamma.Visitors.UIVisitor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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
        Text scenetitle = new Text("Welcome");
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        UIVisitor uivis = new UIVisitor(grid, new DefaultWidgetBuilder(form), stage);
        form.forEach(formItem -> formItem.accept(uivis));

        stage.setScene(new Scene(grid));
        stage.sizeToScene();
        stage.show();
    }

    public void showQuestion(Question question) {

    }
}

