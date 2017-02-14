package org.ql.parser;

import org.junit.Assert;
import org.junit.Test;
import org.ql.ast.expression.literal.Boolean;
import org.ql.ast.expression.literal.Decimal;
import org.ql.ast.Form;
import org.ql.ast.statement.If;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;

public class ParserTest extends Assert {
    @Test
    public void shouldCreateFormNode() {
        Parser parser = new Parser();
        String inputCode = "form MyForm {}";
        String expectedName = "MyForm";

        Form form = parser.parse(inputCode);

        assertTrue(form != null);
        assertEquals(expectedName, form.getName().toString());
    }

    @Test
    public void shouldCreateFormWithQuestionStatements() {
        Parser parser = new Parser();
        String inputCode = "form MyNewForm {" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\";\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\";" +
                "}";

        Form ast = parser.parse(inputCode);

        assertSame(2, ast.getStatements().size());
        assertTrue(ast.getStatement(0) instanceof Question);
        assertEquals("hasSoldHouse", ((Question) ast.getStatement(0)).getId().toString());
        assertEquals("Did you sell a house in 2010?", ((Question) ast.getStatement(0)).getQuestionText().toString());
        assertEquals(Type.BOOLEAN, ((Question) ast.getStatement(0)).getType());
    }

    @Test
    public void shouldCreateFormWithQuestionStatementsHoldingDefaultValues() {
        Parser parser = new Parser();
        String inputCode = "form MyNewForm {" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\" = true;\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\" = false;\n" +
                "    money hasBoughtHouse: \"Did you buy a house in 2010?\" = 145.23;" +
                "}";

        Form ast = parser.parse(inputCode);

        assertEquals(true, ((Boolean) ((Question) ast.getStatement(0)).getDefaultValue()).getBooleanLiteral());
        assertEquals(false, ((Boolean) ((Question) ast.getStatement(1)).getDefaultValue()).getBooleanLiteral());
        assertEquals("145.23", ((Decimal) ((Question) ast.getStatement(2)).getDefaultValue()).getDecimalLiteral().toPlainString());
    }

    @Test
    public void shouldCreateFormWithQuestionsInsideIf() {
        Parser parser = new Parser();
        String inputCode = "form ExampleForm {\n" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\";\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\";\n" +
                "    boolean hasMainLoan:  \"Did you enter a loan?\";\n" +
                "\n" +
                "    if (hasSoldHouse) {\n" +
                "        money sellingPrice: \"What was the selling price?\";\n" +
                "        money privateDebt: \"Private debts for the sold house:\";\n" +
                "    }\n" +
                "}";
        int expectedIfStatementLocation = 3;
        int expectedAmountOfQuestionsInsideIfStatement = 2;

        Form ast = parser.parse(inputCode);
        If ifStatement = (If) ast.getStatement(expectedIfStatementLocation);
        assertSame(expectedAmountOfQuestionsInsideIfStatement, ifStatement.getStatements().size());
        assertEquals("hasSoldHouse", ifStatement.getCondition().toString());
    }

    @Test
    public void shouldCreateFormWithExpression() {
        Parser parser = new Parser();
        String inputCode = "form ExampleForm {\n" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\";\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\";\n" +
                "    boolean hasMaintLoan:  \"Did you enter a loan?\";\n" +
                "\n" +
                "    if (3<4) {\n" +
                "        money sellingPrice: \"What was the selling price?\";\n" +
                "        money privateDebt: \"Private debts for the sold house:\";\n" +
                "    }\n" +
                "}";
        int expectedIfStatementLocation = 3;
        int expectedAmountOfQuestionsInsideIfStatement = 2;

        Form ast = parser.parse(inputCode);
        If ifStatement = (If) ast.getStatement(expectedIfStatementLocation);
        assertSame(expectedAmountOfQuestionsInsideIfStatement, ifStatement.getStatements().size());

        // TODO: More asserts.
    }
}
