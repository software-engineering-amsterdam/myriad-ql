package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ql.ast.environment.Env;
import ql.ast.visistor.EnvASTVisitor;
import ql.ast.visistor.TypeASTVisitor;
import ql.logger.ErrorHandler;
import ql.parser.Parser;
import ql.parser.QLLexer;

import java.io.Reader;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Erik on 14-3-2017.
 */
public class TypeTest {

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
    public void exprTest1() {
        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int = 1 + 5" +
                "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest2() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: int" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int = testQuestion1 * 2" +
                        "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest3() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: int" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int = testQuestion1 * 2.0" +
                        "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest4() {
        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int" +
                "\"test question2?\"\n" +
                "    testQuestion2: float = testQuestion1 * 2.0" +
                "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest5() {
        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: boolean = 1.0" +
                "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest6() {
        String formInput =
                "form ParseTest1 {   " +
                "\"test question1?\"\n" +
                "    testQuestion1: int" +
                "\"test question2?\"\n" +
                "    testQuestion2: int" +
                "\"test question3?\"\n" +
                "    testQuestion3: boolean = testQuestion1 == testQuestion2" +
                "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest7() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: int" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int" +
                        "\"test question3?\"\n" +
                        "    testQuestion3: boolean = testQuestion1 + testQuestion2" +
                        "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest8() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: int" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: float" +
                        "\"test question3?\"\n" +
                        "    testQuestion3: boolean = testQuestion1 > testQuestion2" +
                        "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest9() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: string" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: string" +
                        "\"test question3?\"\n" +
                        "    testQuestion3: boolean = testQuestion1 == testQuestion2" +
                        "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest10() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: boolean" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: boolean" +
                        "\"test question3?\"\n" +
                        "    testQuestion3: boolean = testQuestion1 && testQuestion2" +
                        "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void ExprTest11() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: int" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int" +
                        "\"test question3?\"\n" +
                        "    testQuestion3: boolean = testQuestion1 && testQuestion2" +
                        "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }


    @Test
    public void IfConditionTest1() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: int " +
                        "if (testQuestion1) {" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int " +
                        "}" +
                        "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void IfConditionTest2() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: float " +
                        "if (testQuestion1) {" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int " +
                        "}" +
                        "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void IfConditionTest3() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: string " +
                        "if (testQuestion1) {" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int " +
                        "}" +
                        "}";

        executeTypeCheck(formInput);
        assertTrue(errorHandler.foundErrors());
    }

    @Test
    public void IfConditionTest4() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: boolean " +
                        "if (testQuestion1) {" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int " +
                        "}" +
                        "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    @Test
    public void IfConditionTest5() {
        String formInput =
                "form ParseTest1 {   " +
                        "\"test question1?\"\n" +
                        "    testQuestion1: int " +
                        "if (testQuestion1 >= 0) {" +
                        "\"test question2?\"\n" +
                        "    testQuestion2: int " +
                        "}" +
                        "}";

        executeTypeCheck(formInput);
        assertFalse(errorHandler.foundErrors());
    }

    private void executeTypeCheck(String formInput) {
        Reader reader = new StringReader(formInput);
        QLLexer lexer = new QLLexer(reader);
        lexer.nextToken();

        Parser parser = new Parser(lexer);
        parser.parse();

        EnvASTVisitor envASTVisitor = new EnvASTVisitor();
        Env env = envASTVisitor.startVisitor(errorHandler, parser.getResult());

        assertFalse(errorHandler.foundErrors());

        TypeASTVisitor typeASTVisitor = new TypeASTVisitor(env);
        typeASTVisitor.startVisitor(errorHandler, parser.getResult());
    }
}
