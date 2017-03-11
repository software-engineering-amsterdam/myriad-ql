package org.qls.parser;

import org.junit.Test;
import org.ql.ast.Form;
import org.qls.ast.StyleSheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StyleSheetTest {
    @Test
    public void shouldCreateStyleSheetNode() {
        Parser parser = new Parser();

        StyleSheet styleSheet = parser.parseStyleSheet("stylesheet exampleStyle");

        assertTrue(styleSheet != null);
        assertEquals("exampleStyle", styleSheet.getName().toString());
    }
}
