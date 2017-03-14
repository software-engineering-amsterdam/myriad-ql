package ql;

import javafx.application.Application;
import javafx.stage.Stage;
import ql.ast.environment.Env;
import ql.ast.visistor.*;
import ql.ast.environment.Environment;
import ql.parser.Parser;
import ql.parser.QLLexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by Erik on 7-2-2017.
 */
public class Main extends Application {

    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Reader reader = null;
        try {
            reader = new FileReader("C:\\Users\\Erik\\Documents\\uva\\SC\\QL\\myriad-ql\\TBD\\QL\\test.txt");
            //reader = new FileReader("/home/rico/Desktop/test.txt");
            QLLexer lexer = new QLLexer(reader);
            lexer.nextToken();

            System.out.println("Lexer done");

            Parser parser = new Parser(lexer);
            parser.parse();

            System.out.println("Parser done");

            ASTVisitor<Void> printVisitor = new PrintASTVisitor();
            printVisitor.visit(parser.getResult());

            EnvASTVisitor envASTVisitor = new EnvASTVisitor();
            Env env = envASTVisitor.startVisitor(parser.getResult());

            TypeASTVisitor typeVisitor = new TypeASTVisitor(env);
            typeVisitor.startVisitor(parser.getResult());

            ViewASTVisitor viewASTVisitor = new ViewASTVisitor(env);
            primaryStage.setScene(viewASTVisitor.startVisitor(parser.getResult()));
            primaryStage.show();

            /* On update resize form. */
            env.addEventListener(primaryStage::sizeToScene);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
