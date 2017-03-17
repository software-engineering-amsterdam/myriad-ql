package com.matthewchapman.ql.validation.structure;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.core.CoreParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by matt on 17/03/2017.
 */
public class QLExpressionCheckerTest {

    

    @Before
    public void setUp() {
        CoreParser parser = new CoreParser();
        Form form = parser.getForm(parser.getQlParser("form test { if (parameter1) { \"Test\" testquestion:boolean; } }"));
    }

    @Test
    public void checkExpressions() throws Exception {

    }

}