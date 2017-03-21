package org.qls.parser.widget.default_widget;

import org.junit.Test;
import org.qls.ast.page.Page;
import org.qls.ast.widget.*;
import org.qls.parser.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultWidgetNoStyleTest {
    @Test
    public void shouldContainPageWithDefaultRadioWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default boolean widget radio(\"Yes\", \"No\")\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
    }

    @Test
    public void shouldContainPageWithDefaultSliderWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default float widget slider\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
    }

    @Test
    public void shouldContainPageWithDefaultSpinboxWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default float widget spinbox\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
    }

    @Test
    public void shouldContainPageWithDefaultTextWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default float widget text\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
    }

    @Test
    public void shouldContainPageWithDefaultDropdownWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default boolean widget dropdown(\"Yes\", \"No\")\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
    }

    @Test
    public void shouldContainPageWithDefaultCheckboxWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default boolean widget checkbox\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
    }
}
