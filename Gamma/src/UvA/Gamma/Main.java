package UvA.Gamma;

import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.FormItem;
import UvA.Gamma.Antlr.QL.QLLexer;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.GUI.MainScreen;
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


        String test = "form test {\"how old are you?\" first: integer\n " +
                "\"Our age difference is: \" dif: integer = (21-first)}";

        InputStream is = new ByteArrayInputStream(test.getBytes());
        ANTLRInputStream input = new ANTLRInputStream(is);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree parseTree = parser.form();
        QLVisitor visitor = new QLVisitor();
        visitor.visit(parseTree);
        Form form = visitor.getForm();

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
