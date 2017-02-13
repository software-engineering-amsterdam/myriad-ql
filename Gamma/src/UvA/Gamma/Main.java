package UvA.Gamma;

import UvA.Gamma.Antlr.QL.QLLexer;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.Models.QLForm;
import UvA.Gamma.Models.QLInput;
import UvA.Gamma.Models.QLValues.QLValue;
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
        MainScreen mainScreen = new MainScreen();
        mainScreen.initUI(primaryStage);

        String test = "form test { first: \"how old are you?\" integer \n" +
                "second: \"That is true!\" boolean }";
        InputStream is = new ByteArrayInputStream(test.getBytes());
        ANTLRInputStream input = new ANTLRInputStream(is);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree parseTree = parser.form();
        QLVisitor visitor = new QLVisitor();
        visitor.visit(parseTree);

        QLForm form = visitor.getForm();
        for(QLInput i : form.getInputs()){
            if (i.getType() == QLValue.Type.BOOLEAN){
                i.setValue(false);
            }
            System.out.println(i);
        }

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
