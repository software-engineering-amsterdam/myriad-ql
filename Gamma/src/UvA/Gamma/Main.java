package UvA.Gamma;

import UvA.Gamma.AST.BooleanExpression;
import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.FormItem;
import UvA.Gamma.AST.NumberExpression;
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

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
//        MainScreen mainScreen = new MainScreen();
//        mainScreen.initUI(primaryStage);


        String test = "form test {\"how old are you?\" first: integer = (2+2) \n}";
        InputStream is = new ByteArrayInputStream(test.getBytes());
        ANTLRInputStream input = new ANTLRInputStream(is);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree parseTree = parser.form();
        QLVisitor visitor = new QLVisitor();
        visitor.visit(parseTree);

        System.out.println(new NumberExpression("2.2+2.0").toString());
        System.out.println(new BooleanExpression("true && true").toString());

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
