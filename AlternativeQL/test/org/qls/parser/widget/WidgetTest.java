package org.qls.parser.widget;

import org.junit.Test;
import org.qls.ast.page.Page;
import org.qls.ast.widget.*;
import org.qls.parser.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WidgetTest {
    /*
    @Test
    public void shouldContainQuestionWithSpinboxWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                    "section \"Buying\"\n" +
                    "question hasBoughtHouse\n" +
                    "widget spinbox" +
                "}");

        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() != null);
        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() instanceof SpinboxWidget);
    }

    @Test
    public void shouldContainQuestionWithSliderWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget slider" +
                "}");

        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() != null);
        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() instanceof SliderWidget);
    }

    @Test
    public void shouldContainQuestionWithTextWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget text" +
                "}");

        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() != null);
        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() instanceof TextWidget);
    }

    @Test
    public void shouldContainQuestionWithCheckboxWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget checkbox" +
                "}");

        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() != null);
        assertTrue(page.getSections().get(0).getQuestions().get(0).getWidget() instanceof CheckboxWidget);
    }

    @Test
    public void shouldContainQuestionWithRadioWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget radio(\"yes\", \"no\")" +
                "}");

        Widget widget = page.getSections().get(0).getQuestions().get(0).getWidget();
        assertTrue(widget != null);
        assertTrue(widget instanceof RadioWidget);
        assertEquals("\"yes\"", ((RadioWidget) widget).getYesText());
        assertEquals("\"no\"", ((RadioWidget) widget).getNoText());
    }

    @Test
    public void shouldContainQuestionWithDropdownWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget dropdown(\"yes\", \"no\")" +
                "}");

        Widget widget = page.getSections().get(0).getQuestions().get(0).getWidget();
        assertTrue(widget != null);
        assertTrue(widget instanceof DropdownWidget);
        assertEquals("\"yes\"", ((DropdownWidget) widget).getYesText());
        assertEquals("\"no\"", ((DropdownWidget) widget).getNoText());
    }
    */
}
