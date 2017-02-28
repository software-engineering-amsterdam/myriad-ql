package UvA.Gamma;

import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.FormItem;
import UvA.Gamma.Antlr.QL.QLLexer;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.Validation.IdNotFoundException;
import UvA.Gamma.Validation.IdRedeclaredException;
import UvA.Gamma.Validation.IncompatibleTypesException;
import UvA.Gamma.Validation.Validator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        String test = "form test {" +
                "\"Some complex expression: \" test: decimal = (12*(3+4)/3*(2+3))" +
                "\"How much do you want to pay? \" pay: money\n " +
                "if(pay > 20.0){ \"You paid too much: \" paid: money = (pay - 20) } else { " +
                "\"You paid not enough: \" paid: money = (20 - pay) } }";

        InputStream is = new ByteArrayInputStream(test.getBytes());
        ANTLRInputStream input = new ANTLRInputStream(is);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree parseTree = parser.form();
        QLVisitor visitor = new QLVisitor();
        visitor.visit(parseTree);
        Form form = visitor.getForm();

        Validator checker = new Validator(form);
        try {
            checker.visit();
        } catch (IdNotFoundException | IdRedeclaredException | IncompatibleTypesException ex) {
            System.out.println(ex.getMessage());
        }

        MainScreen mainScreen = new MainScreen(form);
        mainScreen.initUI(primaryStage);

        for (FormItem item : form.getFormItems()) {
            mainScreen.addFormItem(item);
            System.out.println(item);
        }
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

}
