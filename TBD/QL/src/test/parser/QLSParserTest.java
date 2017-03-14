package test.parser;

import org.codehaus.groovy.syntax.SyntaxException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ql.ast.visistor.ASTVisitor;
import ql.ast.visistor.PrintASTVisitor;
import ql.parser.Parser;
import ql.parser.QLLexer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Erik on 14-3-2017.
 */
class QLSParserTest {

    @Test
    public void SimpleParseTest() {

        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int" +
                "\"test question2?\"\n" +
                "    testQuestion2: boolean" +
                "\"test question3?\"\n" +
                "    testQuestion3: string" +
                "\"test question4?\"\n" +
                "    testQuestion4: float" +
                "}";

        Reader reader = new StringReader(formInput);
        QLLexer lexer = new QLLexer(reader);
        lexer.nextToken();

        Parser parser = new Parser(lexer);
        parser.parse();

        ASTVisitor<Void> printVisitor = new PrintASTVisitor();
        printVisitor.visit(parser.getResult());

    }

    @Test
    public void SimpleParseExpr() {

        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int" +
                "\"test question2?\"\n" +
                "    testQuestion2: int = testQuestion1 * 2" +
                "}";

        Reader reader = new StringReader(formInput);
        QLLexer lexer = new QLLexer(reader);
        lexer.nextToken();

        Parser parser = new Parser(lexer);
        parser.parse();

        ASTVisitor<Void> printVisitor = new PrintASTVisitor();
        printVisitor.visit(parser.getResult());
    }



}