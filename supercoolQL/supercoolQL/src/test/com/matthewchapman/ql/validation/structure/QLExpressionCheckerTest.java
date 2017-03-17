package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.atomic.BooleanType;
import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.core.CoreParser;
import com.matthewchapman.ql.core.QLErrorLogger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 17/03/2017.
 */
public class QLExpressionCheckerTest {


    @Test
    public void checkExpressions() {
        int EXPECTED_ERRORS = 1;

        QLExpressionChecker checker = new QLExpressionChecker();
        CoreParser parser = new CoreParser();
        Form form = parser.getForm(parser.getQlParser("form test { if (parameter1) { \"Test\" testquestion:boolean; } if (testquestion2) { \"anothertest\" anothertest:boolean; } }"));
        Map<String, Type> typeTable = new HashMap<String, Type>(){{ put("testquestion2", new BooleanType()); }};

        QLErrorLogger logger = checker.checkExpressions(form, typeTable);

        assertEquals(EXPECTED_ERRORS, logger.getErrorNumber());
    }

}