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

        assertTrue(actualQuestion.getWidget() instanceof SpinboxWidget);
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

        assertTrue(actualQuestion.getWidget() instanceof SliderWidget);
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

        assertTrue(actualQuestion.getWidget() instanceof TextWidget);
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

        assertTrue(actualQuestion.getWidget() instanceof CheckboxWidget);
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

        assertTrue(actualQuestion.getWidget() instanceof RadioWidget);
        assertEquals("yes", ((RadioWidget) actualQuestion.getWidget()).getYesText());
        assertEquals("no", ((RadioWidget) actualQuestion.getWidget()).getNoText());
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

        assertTrue(actualQuestion.getWidget() instanceof DropdownWidget);
        assertEquals("yes", ((DropdownWidget) actualQuestion.getWidget()).getYesText());
        assertEquals("no", ((DropdownWidget) actualQuestion.getWidget()).getNoText());
    }

}
