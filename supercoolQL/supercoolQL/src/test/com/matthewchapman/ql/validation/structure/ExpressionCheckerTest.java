package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.parsing.ASTBuilder;
import com.matthewchapman.ql.errorhandling.ErrorLogger;
import com.matthewchapman.ql.validation.type.TypeTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 17/03/2017.
 */
public class ExpressionCheckerTest {


    @Test
    public void checkExpressions() {
        int EXPECTED_ERRORS = 1;

        ExpressionChecker checker = new ExpressionChecker();
        ASTBuilder parser = new ASTBuilder();
        Form form = parser.getForm(parser.getQlParser("form test { if (parameter1) { \"Test\" testquestion:boolean; } if (testquestion2) { \"anothertest\" anothertest:boolean; } }"));
        TypeTable typeTable = new TypeTable();
        typeTable.addEntry("testquestion2", new BooleanType());

        ErrorLogger logger = checker.checkExpressions(form, typeTable);

        assertEquals(EXPECTED_ERRORS, logger.getErrorNumber());
    }

}