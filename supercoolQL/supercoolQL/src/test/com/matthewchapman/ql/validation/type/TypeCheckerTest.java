package com.matthewchapman.ql.validation.type;

import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.atomic.type.BooleanType;
import com.matthewchapman.ql.ast.atomic.type.IntegerType;
import com.matthewchapman.ql.core.CoreParser;
import com.matthewchapman.ql.core.ErrorLogger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 18/03/2017.
 */
public class TypeCheckerTest {

    private CoreParser coreParser;

    @Before
    public void setUp() {
        coreParser = new CoreParser();
    }

    @Test
    public void checkTypeChecker() {
        final int EXPECTED_RESULT = 2;
        QLParser parser = coreParser.getQlParser("form testform { \"test1\" test1:boolean; \"test2\" test2:integer = 12 + test1");
        Form form = (Form) coreParser.getForm(parser);

        TypeTable typeTable = new TypeTable();
        typeTable.addEntry("test1", new BooleanType());
        typeTable.addEntry("test2", new IntegerType());

        TypeChecker checker = new TypeChecker();
        ErrorLogger logger = checker.checkExpressionTypes(form, typeTable);

        assertEquals(EXPECTED_RESULT, logger.getErrorNumber());

    }

}