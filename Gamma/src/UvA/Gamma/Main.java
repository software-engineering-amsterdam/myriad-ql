package UvA.Gamma;

import UvA.Gamma.AST.Form;
import UvA.Gamma.Antlr.QL.QLLexer;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.Validation.QLParseErrorListener;
import UvA.Gamma.Validation.Validator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                QLVisitor visitor = new QLVisitor();

                //The root element is a form, hence the result can be casted to Form
                Form form = (Form) visitor.visit(parseTree);

                Validator validator = new Validator(form);
                // Will terminate the program with an appropriate message if the QL form is not valid
                validator.visit();

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

    public static void main(String[] args) throws IOException {
        launch(args);
    }

}
