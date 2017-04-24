package org.qls.parser.page;

import org.junit.Test;
import org.qls.ast.page.Page;
import org.qls.parser.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PageTest {

    @Test
    public void shouldContainSection() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                    "section \"Buying\" {}\n" +
                "}");

        assertTrue(page != null);
        assertEquals(1, page.getSections().size());
        assertEquals("Buying", page.getSections().get(0).getName());
    }

    @Test
    public void shouldContainMultipleSections() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                    "section \"Buying\" {}\n" +
                    "section \"Loaning\" {}\n" +
                "}");

        assertTrue(page != null);
        assertEquals(2, page.getSections().size());
        assertEquals("Buying", page.getSections().get(0).getName());
        assertEquals("Loaning", page.getSections().get(1).getName());
    }

    @Test
    public void shouldContainNestedSections() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                    "section \"Buying\" {" +
                        "section \"Loaning\" {" +
                        "}\n" +
                    "}\n" +
                "}");

        assertTrue(page != null);
        assertEquals(1, page.getSections().size());
        assertEquals("Buying", page.getSections().get(0).getName());
        assertEquals("Loaning", page.getSections().get(0).getSections().get(0).getName());
    }
}
