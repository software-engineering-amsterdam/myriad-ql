package UvA.Gamma.GUI;

import UvA.Gamma.AST.Form;
import UvA.Gamma.Validation.TypeChecker;
import UvA.Gamma.Visitors.UIVisitor;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FXMLController {
    private Form form;
    private TypeChecker checker = new TypeChecker();
    private Stage stage;

    private GridPane grid;
    private GridPane rootGrid;

    public FXMLController(Form form) {
        this.form = form;
    }

    public void initVisitor(GridPane grid) {
        UIVisitor uivis = new UIVisitor(grid, new DefaultWidgetBuilder(form));
        form.forEach(formItem -> formItem.accept(uivis));
    }
}