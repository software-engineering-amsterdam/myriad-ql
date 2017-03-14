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
        QLSCheckBox qlsTest = new QLSCheckBox();
        StyleQuestion styleq1 = new StyleQuestion("question", qlsTest, new LineNumber(1));
        StyleQuestion styleq2 = new StyleQuestion("question", qlsTest, new LineNumber(1));

        List<StyleQuestion> questionList = new ArrayList<>();
        questionList.add(styleq1);
        questionList.add(styleq2);

        Section testSection = new Section("testSection", new ArrayList<>(),
                new ArrayList<>(), questionList, new LineNumber(1));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet(sectionList, new ArrayList<>(), "testSheet", new LineNumber(1));

        MessageData messages = new MessageData();

        Map<String, Type> identifierToTypeMap = new HashMap<>();
        identifierToTypeMap.put("question", new BooleanType());

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Duplicate question placement question at line 1.");
    }

    @Test
    public void testNotAllQuestionsDefinedError() {
        QLSCheckBox qlsTest = new QLSCheckBox();
        StyleQuestion styleq1 = new StyleQuestion("question", qlsTest, new LineNumber(1));

        List<StyleQuestion> questionList = new ArrayList<>();
        questionList.add(styleq1);

        Section testSection = new Section("testSection", new ArrayList<>(),
                new ArrayList<>(), questionList, new LineNumber(1));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet(sectionList, new ArrayList<>(), "testSheet", new LineNumber(1));

        MessageData messages = new MessageData();

        Map<String, Type> identifierToTypeMap = new HashMap<>();
        identifierToTypeMap.put("question", new BooleanType());
        identifierToTypeMap.put("undefinedQuestion", new IntegerType());

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Question undefinedQuestion from QL has not been defined in the QLS stylesheet.");
    }

    @Test
    public void testUndefinedQuestionError() {
        QLSCheckBox qlsTest = new QLSCheckBox();
        StyleQuestion styleq1 = new StyleQuestion("question", qlsTest, new LineNumber(1));

        List<StyleQuestion> questionList;
        questionList = new ArrayList<>();

        questionList.add(styleq1);

        Section testSection = new Section("testSection", new ArrayList<>(),
                new ArrayList<>(), questionList, new LineNumber(1));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet(sectionList, new ArrayList<>(), "testSheet", new LineNumber(1));

        MessageData messages = new MessageData();

        Map<String, Type> identifierToTypeMap = new HashMap<>();

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Reference to undefined question in QLS code for question at line 1.");
    }

    @Test
    public void testUnsupportedWidgetTypeError() {
        QLSCheckBox qlsTest = new QLSCheckBox();
        StyleQuestion styleq1 = new StyleQuestion("question", qlsTest, new LineNumber(1));

        List<StyleQuestion> questionList = new ArrayList<>();
        questionList.add(styleq1);

        Section testSection = new Section("testSection", new ArrayList<>(),
                new ArrayList<>(), questionList, new LineNumber(1));

        List<Section> sectionList = new ArrayList<>();
        sectionList.add(testSection);

        StyleSheet testSheet = new StyleSheet(sectionList, new ArrayList<>(), "testSheet", new LineNumber(1));

        MessageData messages = new MessageData();

        Map<String, Type> identifierToTypeMap = new HashMap<>();
        identifierToTypeMap.put("question", new IntegerType());

        new QLSTypeChecker(messages, identifierToTypeMap, testSheet);

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Widget assignment of question at line 1 is not compatible with question type.");
    }
}
