package org.ql.parser;

import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.statement.Question;
import org.ql.ast.type.Type;

import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void shouldCreateFormWithQuestionStatements() {
        Parser parser = new Parser();
        String inputCode = "form MyNewForm {" +
                "    boolean hasSoldHouse: \"Did you sell a house in 2010?\";\n" +
                "    boolean hasBoughtHouse: \"Did you buy a house in 2010?\";" +
                "}";

        Form ast = parser.parseForm(inputCode);

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

        Form ast = parser.parseForm(inputCode);

        assertEquals(true, ((BooleanLiteral) ((Question) ast.getStatement(0)).getDefaultValue()).getValue());
        assertEquals(false, ((BooleanLiteral) ((Question) ast.getStatement(1)).getDefaultValue()).getValue());
        assertEquals("145.23", ((DecimalLiteral) ((Question) ast.getStatement(2)).getDefaultValue()).getValue().toPlainString());
    }
}
