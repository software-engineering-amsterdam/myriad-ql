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
        assertEquals("\"Buying\"", page.getSections().get(0).getName());
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
        assertEquals("\"Buying\"", page.getSections().get(0).getName());
        assertEquals("\"Loaning\"", page.getSections().get(1).getName());
    }

    @Test
    public void shouldContainQuestionWithoutWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "  question hasBoughtHouse\n" +
                "}");

        assertTrue(page != null);
        assertEquals(1, page.getSections().get(0).getQuestions().size());
        assertEquals("hasBoughtHouse", page.getSections().get(0).getQuestions().get(0).getIdentifier().toString());
    }

    @Test
    public void shouldContainMultipleQuestionsWithoutWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n {" +
                    "  question hasBoughtHouse\n" +
                    "  question valueResidue\n" +
                    "}\n" +
                "}");

        assertTrue(page != null);
        assertEquals(2, page.getSections().get(0).getQuestions().size());
        assertEquals("hasBoughtHouse", page.getSections().get(0).getQuestions().get(0).getIdentifier().toString());
        assertEquals("valueResidue", page.getSections().get(0).getQuestions().get(1).getIdentifier().toString());
    }
}
