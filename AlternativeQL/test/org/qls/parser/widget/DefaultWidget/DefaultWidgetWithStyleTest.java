package org.qls.parser.widget.DefaultWidget;

import org.junit.Test;
import org.qls.ast.page.Page;
import org.qls.ast.widget.SpinboxWidget;
import org.qls.ast.widget.defaultWidget.DefaultWidgetWithStyle;
import org.qls.ast.widget.defaultWidget.style.FontRule;
import org.qls.ast.widget.defaultWidget.style.FontSizeRule;
import org.qls.ast.widget.defaultWidget.style.WidthRule;
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

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertTrue(((DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0)).getStyleRules().get(0) instanceof WidthRule);
        WidthRule widthRule = (WidthRule) ((DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0)).getStyleRules().get(0);
        assertEquals(400, widthRule.getWidth());
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

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertTrue(((DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0)).getStyleRules().get(0) instanceof FontRule);
        FontRule widthRule = (FontRule) ((DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0)).getStyleRules().get(0);
        assertEquals("Arial", widthRule.getFont());
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

        assertEquals(1, page.getSections().get(0).getDefaultWidgets().size());
        assertTrue(((DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0)).getStyleRules().get(0) instanceof FontSizeRule);
        FontSizeRule widthRule = (FontSizeRule) ((DefaultWidgetWithStyle) page.getSections().get(0).getDefaultWidgets().get(0)).getStyleRules().get(0);
        assertEquals(14, widthRule.getFontSize());
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
