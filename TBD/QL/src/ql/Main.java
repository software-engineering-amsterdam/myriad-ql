package ql;

import javafx.application.Application;
import javafx.stage.Stage;
import ql.visistor.environment.Env;
import ql.visistor.EnvASTVisitor;
import ql.visistor.PrintASTVisitor;
import ql.visistor.TypeASTVisitor;
import ql.visistor.ViewASTVisitor;
import ql.logger.ErrorHandler;
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

            System.out.println(parser.getResult());

            System.out.println("Parser done");

            PrintASTVisitor printVisitor = new PrintASTVisitor();
            printVisitor.visit(parser.getResult());

            ErrorHandler errorHandler = new ErrorHandler();

            EnvASTVisitor envASTVisitor = new EnvASTVisitor();
            Env env = envASTVisitor.startVisitor(errorHandler, parser.getResult());

            TypeASTVisitor typeVisitor = new TypeASTVisitor(env);
            typeVisitor.startVisitor(errorHandler, parser.getResult());

            ViewASTVisitor viewASTVisitor = new ViewASTVisitor(primaryStage);
            primaryStage.setScene(viewASTVisitor.startVisitor(parser.getResult()));
            primaryStage.show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
