package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ql.visistor.environment.Env;
import ql.ast.types.IntType;
import ql.visistor.EnvASTVisitor;
import ql.logger.ErrorHandler;
import ql.parser.Parser;
import ql.parser.QLLexer;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Erik on 14-3-2017.
 */
public class EnvTest {
    private ErrorHandler errorHandler;

    @BeforeEach
    public void setup() {
        errorHandler = new ErrorHandler(true);
    }

    @AfterEach
    public void cleanup() {
        errorHandler = null;
    }


    @Test
    public void envTest1() {
        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int = 5 * 1" +
                "}";

        Env env = executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
        assertTrue(env.contains("testQuestion1"));
        assertTrue(env.hasQuestionExpr("testQuestion1"));
        assertTrue(env.getQuestionType("testQuestion1") instanceof IntType);
    }

    @Test
    public void envTest2() {
        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int" +
                "\"test question1?\"\n" +
                "    testQuestion1: int" +
                "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void envTest3() {
        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int" +
                "\"test question1?\"\n" +
                "    testQuestion1: boolean" +
                "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void envTest4() {
        String formInput = "form ParseTest {\n" +
                "    \"test question1?\"\n" +
                "        testQuestion1: int\n" +
                "    if (testQuestion1 > 0) {\n" +
                "    \"test question2?\"\n" +
                "        testQuestion2: int\n" +
                "     }else {" +
                "         \"test question3?\"\n" +
                "       testQuestion3: int" +
                "       }\n" +
                "}";

        Env env = executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());

        assertTrue(env.contains("testQuestion1"));
        assertTrue(env.contains("testQuestion2"));
        assertTrue(env.contains("testQuestion3"));

        // Check if testQuestion1 is in the scope of testQuestion2 but not the other way around
        assertTrue(env.getQuestionScope("testQuestion2").getScopes().contains(env.getQuestionScope("testQuestion1")));
        assertFalse(env.getQuestionScope("testQuestion1").getScopes().contains(env.getQuestionScope("testQuestion2")));

        // Check if testQuestion1 is in the scope of testQuestion3 but not the other way around
        assertTrue(env.getQuestionScope("testQuestion3").getScopes().contains(env.getQuestionScope("testQuestion1")));
        assertFalse(env.getQuestionScope("testQuestion1").getScopes().contains(env.getQuestionScope("testQuestion3")));

        // Check if testQuestion2 and testQuestion3 aren't in each others scope
        assertFalse(env.getQuestionScope("testQuestion2").getScopes().contains(env.getQuestionScope("testQuestion3")));
        assertFalse(env.getQuestionScope("testQuestion3").getScopes().contains(env.getQuestionScope("testQuestion2")));
    }


    private Env executeTypeCheck(String formInput) {
        Reader reader = new StringReader(formInput);
        QLLexer lexer = new QLLexer(reader);
        lexer.nextToken();

        Parser parser = new Parser(lexer);
        parser.parse();

        EnvASTVisitor envASTVisitor = new EnvASTVisitor();
        return envASTVisitor.startVisitor(errorHandler, parser.getResult());
    }

}
