/**
 * MessageHandlerTest.java.
 */

package QL.unitTesting;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.Form;
import QL.ASTnodes.expressions.literals.Identifier;
import QL.ASTnodes.expressions.literals.MyBoolean;
import QL.ASTnodes.expressions.literals.MyString;
import QL.ASTnodes.statements.ComputedQuestion;
import QL.ASTnodes.statements.IfStatement;
import QL.ASTnodes.statements.Statement;
import QL.ASTnodes.types.BooleanType;
import QL.ASTnodes.types.StringType;
import org.junit.Assert;
import org.junit.Test;
import QL.semanticChecker.SemanticChecker;
import QL.semanticChecker.formDataStorage.valueData.ValueData;
import QL.semanticChecker.messageHandling.MessageData;

import java.util.ArrayList;
import java.util.List;

public class MessageHandlerTest {

    @Test
    public void testInvalidTypeError() {
        IfStatement ifStat = new IfStatement(new MyString("Not a Boolean!", new LineNumber(1)),
                new ArrayList<>(), new LineNumber(1));

        List<Statement> statements = new ArrayList<>();
        statements.add(ifStat);

        Form form = new Form(new Identifier("testForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();
        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Invalid type at line 1. Type should be Boolean.");
    }

    @Test
    public void testIfExpressionUndefinedError() {
        IfStatement ifStat = new IfStatement(new Identifier("anIdentifier", new LineNumber(1)),
                new ArrayList<>(), new LineNumber(1));

        List<Statement> statements = new ArrayList<>();
        statements.add(ifStat);

        Form form = new Form(new Identifier("testForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();
        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Identifier anIdentifier at line 1 in IF statement condition is undefined.");
    }

    @Test
    public void testCyclicDependencyError() {
        ComputedQuestion q1 = new ComputedQuestion(new Identifier("testQuestion1", new LineNumber(1)),
                "Text testQuestion1",
                new BooleanType(),
                new Identifier("testQuestion2", new LineNumber(2)),
                new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("testQuestion2", new LineNumber(2)),
                "Text testQuestion2",
                new BooleanType(),
                new Identifier("testQuestion1", new LineNumber(1)),
                new LineNumber(2));

        List<Statement> statements = new ArrayList<>();
        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("testForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();
        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Cyclomatic dependency between testQuestion2 and testQuestion1 at line 2.");
    }

    @Test
    public void testDuplicateIdentifierError() {
        ComputedQuestion q1 = new ComputedQuestion(new Identifier("testQuestion1", new LineNumber(1)),
                "Text testQuestion1",
                new BooleanType(),
                new MyBoolean(false, new LineNumber(1)),
                new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("testQuestion1", new LineNumber(2)),
                "Text testQuestion1",
                new StringType(),
                new MyString("Test", new LineNumber(2)),
                new LineNumber(2));

        List<Statement> statements = new ArrayList<>();
        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("testForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();
        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Question testQuestion1 at line 2 has a duplicate identifier with a different type.");
    }


    @Test
    public void testUndefinedQuestionError() {
        ComputedQuestion q1 = new ComputedQuestion(new Identifier("testQuestion1", new LineNumber(1)),
                "Text testQuestion1",
                new BooleanType(),
                new Identifier("testQuestion2", new LineNumber(2)),
                new LineNumber(1));

        List<Statement> statements = new ArrayList<>();
        statements.add(q1);

        Form form = new Form(new Identifier("testForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();
        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Question testQuestion2 at line 2 is undefined.");
    }

    @Test
    public void testDuplicateIdentifierWarning() {
        ComputedQuestion q1 = new ComputedQuestion(new Identifier("testQuestion1", new LineNumber(1)),
                "Text testQuestion1",
                new BooleanType(),
                new MyBoolean(false, new LineNumber(1)),
                new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("testQuestion1", new LineNumber(2)),
                "Text testQuestion2",
                new BooleanType(),
                new MyBoolean(false, new LineNumber(2)),
                new LineNumber(2));

        List<Statement> statements = new ArrayList<>();
        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("testForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();
        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getWarnings().get(0).getMessage(),
                "WARNING: Question testQuestion1 at line 2 has a duplicate identifier with the same type.");
    }

    @Test
    public void testDuplicateLabelWarning() {
        ComputedQuestion q1 = new ComputedQuestion(new Identifier("testQuestion1", new LineNumber(1)),
                "Question text",
                new BooleanType(),
                new MyBoolean(false, new LineNumber(1)),
                new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("testQuestion2", new LineNumber(2)),
                "Question text",
                new BooleanType(),
                new MyBoolean(false, new LineNumber(2)),
                new LineNumber(2));

        List<Statement> statements = new ArrayList<>();
        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("testForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();
        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getWarnings().get(0).getMessage(),
                "WARNING: Question testQuestion2 at line 2 has a duplicate label: Question text.");
    }
}
