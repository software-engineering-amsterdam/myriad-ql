/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/unittests/QLSMessageTest.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.unittests;

import org.junit.Assert;
import org.junit.Test;
import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.Type;
import ql.semanticchecker.messagehandling.MessageData;
import qls.astnodes.StyleSheet;
import qls.astnodes.sections.Section;
import qls.astnodes.sections.StyleQuestion;
import qls.astnodes.widgets.QLSCheckBox;
import qls.semanticchecker.QLSTypeChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QLSMessageTest {

    @Test
    public void testDuplicateQuestionPlacementError() {
        QLSCheckBox testWidget = new QLSCheckBox("aLabel", null);
        StyleQuestion styleQuestion1 = new StyleQuestion("question", testWidget, new LineNumber(3));
        StyleQuestion styleQuestion2 = new StyleQuestion("question", testWidget, new LineNumber(4));

        List<StyleQuestion> questionList = new ArrayList<>();
        questionList.add(styleQuestion1);
        questionList.add(styleQuestion2);

        Section testSection = new Section("testSection", new ArrayList<>(), new ArrayList<>(), questionList,
                new LineNumber(2));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet("testSheet", sectionList, new ArrayList<>(), new LineNumber(1));

        MessageData messages = new MessageData();
        Map<String, Type> identifierToTypeMap = new HashMap<>();
        identifierToTypeMap.put("question", new BooleanType());

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Duplicate question placement question at line 4 in QLS stylesheet.");
    }

    @Test
    public void testNotAllQuestionsDefinedError() {
        QLSCheckBox testWidget = new QLSCheckBox("aLabel", null);
        StyleQuestion styleQuestion = new StyleQuestion("question", testWidget, new LineNumber(3));

        List<StyleQuestion> questionList = new ArrayList<>();
        questionList.add(styleQuestion);

        Section testSection = new Section("testSection", new ArrayList<>(), new ArrayList<>(), questionList,
                new LineNumber(2));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet("testSheet", sectionList, new ArrayList<>(), new LineNumber(1));

        MessageData messages = new MessageData();
        Map<String, Type> identifierToTypeMap = new HashMap<>();
        identifierToTypeMap.put("question", new BooleanType());
        identifierToTypeMap.put("undefinedQuestion", new IntegerType());

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Question undefinedQuestion from QL form has not been defined in the QLS stylesheet.");
    }

    @Test
    public void testUndefinedQuestionError() {
        QLSCheckBox testWidget = new QLSCheckBox("aLabel", null);
        StyleQuestion styleQuestion = new StyleQuestion("question", testWidget, new LineNumber(3));

        List<StyleQuestion> questionList = new ArrayList<>();
        questionList.add(styleQuestion);

        Section testSection = new Section("testSection", new ArrayList<>(), new ArrayList<>(), questionList,
                new LineNumber(2));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet("testSheet", sectionList, new ArrayList<>(), new LineNumber(1));

        MessageData messages = new MessageData();
        Map<String, Type> identifierToTypeMap = new HashMap<>();

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Reference to undefined question in QLS stylesheet for question at line 3.");
    }

    @Test
    public void testUnsupportedWidgetTypeError() {
        QLSCheckBox testWidget = new QLSCheckBox("aLabel", null);
        StyleQuestion styleQuestion = new StyleQuestion("question", testWidget, new LineNumber(3));

        List<StyleQuestion> questionList = new ArrayList<>();
        questionList.add(styleQuestion);

        Section testSection = new Section("testSection", new ArrayList<>(), new ArrayList<>(), questionList,
                new LineNumber(2));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet("testSheet", sectionList, new ArrayList<>(), new LineNumber(1));

        MessageData messages = new MessageData();
        Map<String, Type> identifierToTypeMap = new HashMap<>();
        identifierToTypeMap.put("question", new IntegerType());

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Widget assignment of question at line 3 in QLS stylesheet is not compatible with question type.");
    }
}
