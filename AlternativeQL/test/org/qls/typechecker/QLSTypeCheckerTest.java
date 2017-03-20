package org.qls.typechecker;

import org.junit.Test;
import org.ql.ast.form.Form;
import org.ql.parser.Parser;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;
import org.qls.typechecker.issues.errors.DuplicateQuestion;

import static org.junit.Assert.*;

public class QLSTypeCheckerTest {
    @Test
    public void shouldHaveDuplicateQLSQuestionIssue() {
        QLSTypeChecker qlsTypeChecker = new QLSTypeChecker();
        org.qls.parser.Parser styleSheetParser = new org.qls.parser.Parser();

        Form form = createForm();
        StyleSheet styleSheet = styleSheetParser.parseStyleSheet(
                "stylesheet taxOfficeExample\n" +
                "\n" +
                "page Housing {\n" +
                "   section \"Buying\" {\n" +
                "      question firstName\n" +
                "        widget slider\n" +
                "      question firstName\n" +
                "        widget slider\n" +
                "      question lastName\n" +
                "        widget slider\n" +
                "      question fullName\n" +
                "        widget slider\n" +
                "      question areYouSure\n" +
                "        widget checkbox\n" +
                        "}" +
                "}");

        IssuesStorage issueStorage = qlsTypeChecker.checkStyleSheet(form, styleSheet);
        assertSame(1, issueStorage.getErrors().size());
        assertTrue(issueStorage.getErrors().get(0) instanceof DuplicateQuestion);
    }


    // Uncomment if Todo regarding check if QuestionType is null is fixed.
    /*@Test
    public void shouldHaveUndefinedQLQuestion() {
        QLSTypeChecker qlsTypeChecker = new QLSTypeChecker();
        org.qls.parser.Parser styleSheetParser = new org.qls.parser.Parser();

        Form form = createForm();
        StyleSheet styleSheet = styleSheetParser.parseStyleSheet(
                "stylesheet taxOfficeExample\n" +
                        "\n" +
                        "page Housing {\n" +
                        "    section \"Buying\"\n" +
                        "      question firstName\n" +
                        "        widget slider\n" +
                        "    section \"Loaning\"\n" +
                        "      question nonExistentQuestion\n" +
                        "        widget slider\n" +
                        "}");

        IssuesStorage issueStorage = qlsTypeChecker.checkStyleSheet(form, styleSheet);
        assertSame(1, issueStorage.getErrors().size());
        assertTrue(issueStorage.getErrors().get(0) instanceof UndefinedQLQuestion);
    }*/

    private Form createForm() {
        Parser parser = new Parser();
        String inputCode =
                "form TestForm {\n" +
                "    money firstName: \"Enter your first name\";\n" +
                "    money lastName: \"Enter your last name\";\n" +
                "    boolean areYouSure: \"Are you sure?\" = false;\n" +
                "\n" +
                "    if (areYouSure) {\n" +
                "        float fullName: \"This is your full name\" = firstName + lastName;\n" +
                "    }\n" +
                "}";

        return parser.parseForm(inputCode);
    }
}
