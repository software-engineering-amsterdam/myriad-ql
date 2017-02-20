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
import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainScreen mainScreen = new MainScreen();
        mainScreen.initUI(primaryStage);


        String test = "form test {\"how old are you?\" first: integer\n " +
                "\"Our age difference is: \" dif: integer = (21-18)}";
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

        Form form = visitor.getForm();
        System.out.println(new MathExpr("3/2").evaluate());
//        for(QLInput i : form.getInputs()){
//            if (i.getType() == QLValue.Type.BOOLEAN){
//                i.setValue(false);
//            }
//            System.out.println(i);
//        }
        for (FormItem item : form.getFormItems()){
            mainScreen.addFormItem(item);
            System.out.println(item);
        }

        //filter items with id "first"
        List<FormItem> items = form.getFormItems().stream().filter(fi -> fi.hasID("first")).collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
