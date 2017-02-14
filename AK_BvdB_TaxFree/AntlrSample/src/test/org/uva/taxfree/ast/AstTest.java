package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;
import org.uva.taxfree.ast.Ast;

public class AstTest {
    @Test
    public void testEmptyForm() {
        Ast ast = Ast.generateAst("form {}");
        assert "FORM".equals(ast.getRootNode().getId());
    }

    @Test
    public void testSingleQuestionForm() {
        Ast ast = Ast.generateAst(
                "form {" +
                "   \"Have you sold an house last year?\" -> hasSoldHouse : bool" +
                "}");
        assert "FORM".equals(ast.getRootNode().getId());
    }

    @Test
    public void testMultipleQuestionForm() {
        Ast ast = Ast.generateAst(
                "form {" +
                "   \"Have you sold an house last year?\" -> hasSoldHouse : bool" +
                "   \"Have you bought an house last year?\" -> hasBoughtHouse : bool" +
                "}");
        assert "FORM".equals(ast.getRootNode().getId());
    }

    @Test
    public void testEmptyIfForm() {
        Ast ast = Ast.generateAst(
                "form {" +
                        "   \"Have you sold an house last year?\" -> hasSoldHouse : bool" +
                        "   if (hasSoldHouse) {" +
                        "   }" +
                        "}");
        assert "FORM".equals(ast.getRootNode().getId());
    }

    @Test
    public void testIfWithQuestionsForm() {
        Ast ast = Ast.generateAst(
                "form {" +
                "   \"Have you sold an house last year?\" -> hasSoldHouse : bool" +
                "   if (hasSoldHouse) {" +
                "       \"Have you bought an house last year?\" -> hasBoughtHouse : bool" +
                "   }" +
                "}");
        assert "FORM".equals(ast.getRootNode().getId());
    }
}