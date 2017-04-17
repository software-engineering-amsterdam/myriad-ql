//package UvA.Gamma.GUI;
//
//import UvA.Gamma.AST.Form;
//import UvA.Gamma.AST.Question;
//import UvA.Gamma.Validation.TypeChecker;
//import UvA.Gamma.Visitors.UIVisitor;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//public class FXMLController {
//    private Form form;
//    private TypeChecker checker = new TypeChecker();
//    private Stage stage;
//
//    private final double VERTICAL_GAP = 10;
//
//    private GridPane grid;
//    private GridPane rootGrid;
//
//    public FXMLController(Form form) {
//        this.form = form;
//    }
//
//    public void initVisitor(GridPane grid) {
//        UIVisitor uivis = new UIVisitor(grid, new DefaultWidgetBuilder(form), stage);
//        form.forEach(formItem -> formItem.accept(uivis));
//    }

