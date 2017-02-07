package org.ql.parser;

import org.junit.Assert;
import org.junit.Test;
import org.ql.ast.Form;

public class ParserTest extends Assert {
    @Test
    public void shouldReturnFormNode() {
        assertTrue((new Parser()).parse("form MyForm {}") != null);
    }
}
