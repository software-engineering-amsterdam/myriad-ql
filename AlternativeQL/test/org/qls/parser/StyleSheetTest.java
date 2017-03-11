package org.qls.parser;

import org.junit.Test;
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

    @Test
    public void shouldContainPages() {
        Parser parser = new Parser();

        StyleSheet styleSheet = parser.parseStyleSheet("stylesheet taxOfficeExample\n" +
                "page Housing {\n" +
                "}" +
                "page AnotherPage {" +
                "}");

        assertEquals(2, styleSheet.getPages().size());
        assertEquals("Housing", styleSheet.getPages().get(0).getIdentifier().toString());
        assertEquals("AnotherPage", styleSheet.getPages().get(1).getIdentifier().toString());
    }
}
