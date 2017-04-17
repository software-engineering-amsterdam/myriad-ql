package UvA.Gamma;

import UvA.Gamma.AST.Form;
import UvA.Gamma.Antlr.QL.QLLexer;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.Validation.QLParseErrorListener;
import UvA.Gamma.Validation.ReferenceValidator;
import UvA.Gamma.Validation.TypeValidator;
import UvA.Gamma.Validation.ValidationVisitor;
import UvA.Gamma.Visitors.UIVisitor;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parameters params = getParameters();
        if (params.getUnnamed().size() > 0) {
            try {
                InputStream is = new ByteArrayInputStream(Files.readAllBytes(Paths.get(params.getUnnamed().get(0))));
                ANTLRInputStream input = new ANTLRInputStream(is);
                QLLexer lexer = new QLLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                QLParser parser = new QLParser(tokens);
                parser.removeErrorListeners();
                parser.addErrorListener(new QLParseErrorListener());
                ParseTree parseTree = parser.form();
                ASTBuilder visitor = new ASTBuilder();

                //The root element is a form, hence the result can be casted to Form
                Form form = (Form) visitor.visit(parseTree);

                validateForm(form);

                GridPane grid = new GridPane();
                UIVisitor uivisit = new UIVisitor(grid);
                form.forEach(formItem -> formItem.accept(uivisit));

                //System.err.println("Exiting early for testing");
                //System.exit(1);

                MainScreen mainScreen = new MainScreen(form);
                mainScreen.initUI(primaryStage);
            } catch (IOException ex) {
                System.err.print("Invalid filepath: " + params.getUnnamed().get(0));
                System.exit(1);
            }
        } else {
            System.err.println("No input file has been given");
            System.exit(1);
        }
    }

    private void validateForm(Form form) {
        ValidationVisitor validationVisitor = new ValidationVisitor(form);
        form.forEach(formItem -> formItem.accept(validationVisitor));

        validateReferences(form, validationVisitor.getIdentifierStrings());
        validateTypes(form);
    }

    private void validateTypes(Form form) {
        TypeValidator typeValidator = new TypeValidator();
        form.forEach(formItem -> formItem.accept(typeValidator));
    }

    private void validateReferences(Form form, Set<String> identifierStrings) {
        ReferenceValidator referenceValidator = new ReferenceValidator(identifierStrings);
        form.forEach(formItem -> formItem.accept(referenceValidator));
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

}
