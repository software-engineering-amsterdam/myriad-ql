package org.qls.parser.widget.default_widget;

import org.junit.Test;
import org.qls.ast.page.Page;
import org.qls.ast.widget.SpinboxWidget;
import org.qls.ast.widget.default_widget.DefaultWidgetWithStyle;
import org.qls.ast.widget.default_widget.style.FontRule;
import org.qls.ast.widget.default_widget.style.FontSizeRule;
import org.qls.ast.widget.default_widget.style.StyleRule;
import org.qls.ast.widget.default_widget.style.WidthRule;
import org.qls.parser.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultWidgetWithStyleTest {
    @Test
    public void shouldContainDefaultWidgetWithStyles() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "  question valueResidue\n" +
                "  default money {\n" +
                "    width: 400\n" +
                "    font: \"Arial\"\n" +
                "    fontsize: 14\n" +
                "    widget spinbox\n" +
                "  }\n" +
                "}\n" +
                "}");

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertEquals(3, ((DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0)).getStyleRules().size());
    }

    @Test
    public void shouldContainDefaultWidgetWithWidthStyleRule() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "  question valueResidue\n" +
                "  default money {\n" +
                "    width: 400\n" +
                "    widget spinbox\n" +
                "  }\n" +
                "}\n" +
                "}");

        DefaultWidgetWithStyle defaultWidgetWithStyle = (DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0);
        StyleRule widthRule = defaultWidgetWithStyle.getStyleRules().iterator().next();

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertTrue(widthRule instanceof WidthRule);
        assertEquals(400, ((WidthRule) widthRule).getWidth());
    }

    @Test
    public void shouldContainDefaultWidgetWithFontStyleRule() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "  question valueResidue\n" +
                "  default money {\n" +
                "    font: \"Arial\"\n" +
                "    widget spinbox\n" +
                "  }\n" +
                "}\n" +
                "}");

        DefaultWidgetWithStyle defaultWidgetWithStyle = (DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0);
        StyleRule fontRule = defaultWidgetWithStyle.getStyleRules().iterator().next();

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertTrue(fontRule instanceof FontRule);
        assertEquals("Arial", ((FontRule)fontRule).getFont());
    }

    @Test
    public void shouldContainDefaultWidgetWithFontSizeRule() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "  question valueResidue\n" +
                "  default money {\n" +
                "    fontsize: 14\n" +
                "    widget spinbox\n" +
                "  }\n" +
                "}\n" +
                "}");

        DefaultWidgetWithStyle defaultWidgetWithStyle = (DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0);
        StyleRule fontSizeRule = defaultWidgetWithStyle.getStyleRules().iterator().next();

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertTrue(fontSizeRule instanceof FontSizeRule);
        assertEquals(14, ((FontSizeRule) fontSizeRule).getFontSize());
    }

    @Test
    public void shouldContainDefaultWidgetWithStyleSpinbox() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Selling {\n" +
                "section \"Selling\" {\n" +
                "  question valueResidue\n" +
                "  default money {\n" +
                "    fontsize: 14\n" +
                "    widget spinbox\n" +
                "  }\n" +
                "}\n" +
                "}");

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertTrue((page.getSections().get(0).getDefaultWidgets().get(0)).getWidget() instanceof SpinboxWidget);
    }
}
