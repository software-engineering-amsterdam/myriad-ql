package org.qls.typechecker;

import org.junit.Test;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.Addition;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.Statement;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.FloatType;
import org.ql.ast.type.MoneyType;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;
import org.qls.ast.page.CustomWidgetQuestion;
import org.qls.ast.page.Page;
import org.qls.ast.page.Section;
import org.qls.ast.page.WidgetQuestion;
import org.qls.ast.widget.CheckboxWidget;
import org.qls.ast.widget.SliderWidget;
import org.qls.typechecker.issues.errors.DuplicateQuestion;
import org.qls.typechecker.issues.errors.UndefinedQuestion;
import org.qls.typechecker.issues.errors.InconsistentWidgetWithQuestionType;
import org.qls.typechecker.issues.errors.UnusedQuestion;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class QLSTypeCheckerTest {
    @Test
    public void shouldGiveErrorsWhenDuplicateQLSQuestionsExist() {
        QLSTypeChecker qlsTypeChecker = new QLSTypeChecker();

        IssuesStorage issueStorage = qlsTypeChecker.checkStyleSheet(createForm(), new StyleSheet(
            new Identifier("taxOfficeExample"), new ArrayList<Page>() {{
                add(new Page(new Identifier("Housing"), new ArrayList<Section>() {{
                    add(new Section("Buying", new ArrayList<WidgetQuestion>() {{
                        add(new CustomWidgetQuestion(new Identifier("firstName"), new SliderWidget()));
                        add(new CustomWidgetQuestion(new Identifier("firstName"), new SliderWidget()));
                        add(new CustomWidgetQuestion(new Identifier("lastName"), new SliderWidget()));
                        add(new CustomWidgetQuestion(new Identifier("fullName"), new SliderWidget()));
                        add(new CustomWidgetQuestion(new Identifier("areYouSure"), new CheckboxWidget()));
                    }}, new ArrayList<>(), new ArrayList<>()));
                }}, new ArrayList<>()));
            }}
        ));

        assertSame(1, issueStorage.getErrors().size());
        assertTrue(issueStorage.getErrors().get(0) instanceof DuplicateQuestion);
    }

    @Test
    public void shouldGiveErrorWhenUndefinedQuestionIsUsed() {
        QLSTypeChecker qlsTypeChecker = new QLSTypeChecker();

        IssuesStorage issueStorage = qlsTypeChecker.checkStyleSheet(createForm(), new StyleSheet(
                new Identifier("taxOfficeExample"), new ArrayList<Page>() {{
            add(new Page(new Identifier("Housing"), new ArrayList<Section>() {{
                add(new Section("Buying", new ArrayList<WidgetQuestion>() {{
                    add(new CustomWidgetQuestion(new Identifier("firstName"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("undefined"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("lastName"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("fullName"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("areYouSure"), new CheckboxWidget()));
                }}, new ArrayList<>(), new ArrayList<>()));
            }}, new ArrayList<>()));
        }}
        ));

        assertSame(1, issueStorage.getErrors().size());
        assertTrue(issueStorage.getErrors().get(0) instanceof UndefinedQuestion);
    }

    @Test
    public void shouldGiveErrorWhenCheckboxIsUsedForStringQuestion() {
        QLSTypeChecker qlsTypeChecker = new QLSTypeChecker();

        IssuesStorage issueStorage = qlsTypeChecker.checkStyleSheet(createForm(), new StyleSheet(
                new Identifier("taxOfficeExample"), new ArrayList<Page>() {{
            add(new Page(new Identifier("Housing"), new ArrayList<Section>() {{
                add(new Section("Buying", new ArrayList<WidgetQuestion>() {{
                    add(new CustomWidgetQuestion(new Identifier("firstName"), new CheckboxWidget()));
                    add(new CustomWidgetQuestion(new Identifier("lastName"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("fullName"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("areYouSure"), new CheckboxWidget()));
                }}, new ArrayList<>(), new ArrayList<>()));
            }}, new ArrayList<>()));
        }}
        ));

        assertSame(1, issueStorage.getErrors().size());
        assertTrue(issueStorage.getErrors().get(0) instanceof InconsistentWidgetWithQuestionType);
    }

    @Test
    public void shouldGiveErrorWhenThereIsUnusedQLQuestion() {
        QLSTypeChecker qlsTypeChecker = new QLSTypeChecker();

        IssuesStorage issueStorage = qlsTypeChecker.checkStyleSheet(createForm(), new StyleSheet(
                new Identifier("taxOfficeExample"), new ArrayList<Page>() {{
            add(new Page(new Identifier("Housing"), new ArrayList<Section>() {{
                add(new Section("Buying", new ArrayList<WidgetQuestion>() {{
                    add(new CustomWidgetQuestion(new Identifier("lastName"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("fullName"), new SliderWidget()));
                    add(new CustomWidgetQuestion(new Identifier("areYouSure"), new CheckboxWidget()));
                }}, new ArrayList<>(), new ArrayList<>()));
            }}, new ArrayList<>()));
        }}
        ));

        assertSame(1, issueStorage.getErrors().size());
        assertTrue(issueStorage.getErrors().get(0) instanceof UnusedQuestion);
    }

    private Form createForm() {
        Question firstQuestion = new Question(
                new Identifier("firstName"), new QuestionLabel("Enter your first name"), new MoneyType());
        Question secondQuestion = new Question(
                new Identifier("lastName"), new QuestionLabel("Enter your last name"), new MoneyType());
        ComputableQuestion thirdQuestion = new ComputableQuestion(
                new Identifier("areYouSure"), new QuestionLabel("Are you sure?"), new BooleanType(), new BooleanLiteral(false));

        return new Form(new Identifier("TestForm"), new ArrayList<Statement>() {{
            add(firstQuestion);
            add(secondQuestion);
            add(thirdQuestion);
            add(new IfThen(new Parameter(new Identifier("areYouSure")), new ArrayList<Statement>() {{
                add(new ComputableQuestion(new Identifier("fullName"), new QuestionLabel("This is your full name"),
                    new FloatType(),
                    new Addition(
                            new Parameter(new Identifier("firstName")),
                            new Parameter(new Identifier("lastName"))
                    ))
                );
            }}));
        }});
    }
}
