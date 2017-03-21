package org.qls.parser.widget;

import org.junit.Test;
import org.qls.ast.page.CustomWidgetQuestion;
import org.qls.ast.page.Page;
import org.qls.ast.widget.*;
import org.qls.parser.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WidgetTest {

    @Test
    public void shouldContainQuestionWithSpinboxWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                    "section \"Buying\"\n" +
                    "question hasBoughtHouse\n" +
                    "widget spinbox" +
                "}");

        CustomWidgetQuestion actualQuestion = (CustomWidgetQuestion) page.getSections().get(0).getQuestions().get(0);

        assertTrue(actualQuestion.getWidget() instanceof Spinbox);
    }

    @Test
    public void shouldContainQuestionWithSliderWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget slider" +
                "}");

        CustomWidgetQuestion actualQuestion = (CustomWidgetQuestion) page.getSections().get(0).getQuestions().get(0);

        assertTrue(actualQuestion.getWidget() instanceof Slider);
    }

    @Test
    public void shouldContainQuestionWithTextWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget text" +
                "}");

        CustomWidgetQuestion actualQuestion = (CustomWidgetQuestion) page.getSections().get(0).getQuestions().get(0);

        assertTrue(actualQuestion.getWidget() instanceof Text);
    }

    @Test
    public void shouldContainQuestionWithCheckboxWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget checkbox" +
                "}");

        CustomWidgetQuestion actualQuestion = (CustomWidgetQuestion) page.getSections().get(0).getQuestions().get(0);

        assertTrue(actualQuestion.getWidget() instanceof Checkbox);
    }

    @Test
    public void shouldContainQuestionWithRadioWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget radio(\"yes\", \"no\")" +
                "}");

        CustomWidgetQuestion actualQuestion = (CustomWidgetQuestion) page.getSections().get(0).getQuestions().get(0);

        assertTrue(actualQuestion.getWidget() instanceof Radio);
        assertEquals("yes", ((Radio) actualQuestion.getWidget()).getYesText());
        assertEquals("no", ((Radio) actualQuestion.getWidget()).getNoText());
    }

    @Test
    public void shouldContainQuestionWithDropdownWidget() {
        Parser parser = new Parser();

        Page page = parser.parsePage("page Housing {\n" +
                "section \"Buying\"\n" +
                "question hasBoughtHouse\n" +
                "widget dropdown(\"yes\", \"no\")" +
                "}");

        CustomWidgetQuestion actualQuestion = (CustomWidgetQuestion) page.getSections().get(0).getQuestions().get(0);

        assertTrue(actualQuestion.getWidget() instanceof Dropdown);
        assertEquals("yes", ((Dropdown) actualQuestion.getWidget()).getYesText());
        assertEquals("no", ((Dropdown) actualQuestion.getWidget()).getNoText());
    }

}
