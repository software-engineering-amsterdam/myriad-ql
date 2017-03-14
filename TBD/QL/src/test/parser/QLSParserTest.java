package test.parser;

import org.junit.jupiter.api.Test;
import ql.ast.visistor.PrintASTVisitor;
import ql.parser.Parser;
import ql.parser.QLLexer;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Erik on 14-3-2017.
 */
class QLSParserTest {

    @Test
    public void SimpleParseTest() {
        System.out.println("Execute - Simple parse test");

        String formInput =
                "form ParseTest1 {   " +
                "\"Did you sell a house in 2010?\"\n" +
                "    hasSoldHouse: int" +
                "}";

        Reader reader = new StringReader(formInput);
        QLLexer lexer = new QLLexer(reader);
        lexer.nextToken();

        Parser parser = new Parser(lexer);
        parser.parse();

        assertEquals(2, 2);
    }



}