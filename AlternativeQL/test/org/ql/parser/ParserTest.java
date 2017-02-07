package org.ql.parser;

import org.junit.Assert;
import org.junit.Test;
import org.ql.ast.Form;
import org.ql.ast.Identifier;

public class ParserTest extends Assert {
    @Test
    public void shouldReturnFormNode() {
        assertTrue((new Parser()).parse("form MyForm {}") != null);
        assertEquals((new Parser()).parse("form MyForm {}").getName().toString(), "MyForm");
    }
}
