package org.ql.parser.expression.statement;

import org.junit.Test;
import org.ql.ast.statement.IfThenElse;
import org.ql.parser.Parser;

import static org.junit.Assert.*;

public class IfThenElseTest {

    @Test
    public void shouldParseEmptyIfWithEmptyElse() {
        String inputCode = "if (1) {} else {}";
        int expectedSizeOfThenBody = 0;
        int expectedSizeOfElseBody = 0;

        IfThenElse actualIfThenElse = (IfThenElse) new Parser().parseStatement(inputCode);

        assertSame(expectedSizeOfThenBody, actualIfThenElse.getThenStatements().size());
        assertSame(expectedSizeOfElseBody, actualIfThenElse.getElseStatements().size());
    }

    @Test
    public void shouldParseEmptyIfWithAStatementInElse() {
        String inputCode = "if (1) {} else {" +
                "   integer packages: \"Enter number of packages\";" +
                "}";
        int expectedSizeOfThenBody = 0;
        int expectedSizeOfElseBody = 1;

        IfThenElse actualIfThenElse = (IfThenElse) new Parser().parseStatement(inputCode);

        assertSame(expectedSizeOfThenBody, actualIfThenElse.getThenStatements().size());
        assertSame(expectedSizeOfElseBody, actualIfThenElse.getElseStatements().size());
    }
}
