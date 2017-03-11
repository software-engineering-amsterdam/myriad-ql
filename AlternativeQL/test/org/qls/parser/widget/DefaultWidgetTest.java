package org.qls.parser.widget;

import org.junit.Test;
import org.qls.ast.page.Page;
import org.qls.ast.widget.CheckboxWidget;
import org.qls.ast.widget.RadioWidget;
import org.qls.ast.widget.SliderWidget;
import org.qls.parser.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultWidgetTest {
    @Test
    public void shouldContainPageWithDefaultBooleanRadioWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default boolean widget radio(\"Yes\", \"No\")\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
        assertTrue(page.getDefaultWidgets().get(0).getWidget() instanceof RadioWidget);
        assertTrue(page.getDefaultWidgets().get(0).getType().isBoolean());
    }

    @Test
    public void shouldContainPageWithDefaultFloatSliderWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "}\n" +
                "default float widget slider\n" +
                "}");

        assertTrue(page.getDefaultWidgets() != null);
        assertEquals(1, page.getDefaultWidgets().size());
        assertTrue(page.getDefaultWidgets().get(0).getWidget() instanceof SliderWidget);
        assertTrue(page.getDefaultWidgets().get(0).getType().isNumeric());
    }
}
