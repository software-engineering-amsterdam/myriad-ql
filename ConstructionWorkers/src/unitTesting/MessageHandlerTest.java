package unitTesting;

import ASTnodes.LineNumber;
import ASTnodes.Form;
import ASTnodes.expressions.literals.Identifier;
import ASTnodes.expressions.literals.MyBoolean;
import ASTnodes.expressions.literals.MyString;
import ASTnodes.statements.ComputedQuestion;
import ASTnodes.statements.IfStatement;
import ASTnodes.statements.Statement;
import ASTnodes.types.BooleanType;
import ASTnodes.types.StringType;
import org.junit.Assert;
import org.junit.Test;
import semanticChecker.SemanticChecker;
import semanticChecker.formDataStorage.valueData.ValueData;
import semanticChecker.messageHandling.MessageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGGX on 23-Feb-17.
 */
public class MessageHandlerTest {

    @Test
    public void testInvalidExpressionTypeError() {

        IfStatement ifStat = new IfStatement(new MyString("Not a Boolean!", new LineNumber(1)),
                new ArrayList<Statement>(), new LineNumber(1));

        List<Statement> statements = new ArrayList<>();
        statements.add(ifStat);

        Form form = new Form(new Identifier("TestForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();

        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Invalid type at line 1. Type should be Boolean.");
    }

    @Test
    public void testIfConditionUndefinedError() {

        IfStatement ifStat = new IfStatement(new Identifier("testQuestion", new LineNumber(1)),
                new ArrayList<Statement>(), new LineNumber(1));

        List<Statement> statements = new ArrayList<>();
        statements.add(ifStat);

        Form form = new Form(new Identifier("TestForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();

        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Identifier testQuestion at line 1 in IF statement condition is undefined.");
    }

    @Test
    public void testCyclicDependencyError() {

        ComputedQuestion q1 = new ComputedQuestion(new Identifier("TestQuestion1", new LineNumber(1)),
                "Question text 1", new BooleanType(), new Identifier("TestQuestion2",
                new LineNumber(2)), new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("TestQuestion2", new LineNumber(2)),
                "Question text 2", new BooleanType(), new Identifier("TestQuestion1",
                new LineNumber(1)), new LineNumber(2));

        List<Statement> statements = new ArrayList<>();

        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("TestForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();

        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Cyclomatic dependency between TestQuestion2 and TestQuestion1 at line 2.");
    }

    @Test
    public void testDuplicateIdentifierError() {

        ComputedQuestion q1 = new ComputedQuestion(new Identifier("TestQuestion1", new LineNumber(1)),
                "Question text 1", new BooleanType(), new MyBoolean(false,
                new LineNumber(1)), new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("TestQuestion1", new LineNumber(2)),
                "Question text 2", new StringType(), new MyString("Test",
                new LineNumber(2)), new LineNumber(2));

        List<Statement> statements = new ArrayList<>();

        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("TestForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();

        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Question TestQuestion1 at line 2 has a duplicate identifier with a different type.");
    }


    @Test
    public void testUndefinedQuestionError() {

        ComputedQuestion q1 = new ComputedQuestion(new Identifier("TestQuestion1", new LineNumber(1)),
                "Question text 1", new BooleanType(), new Identifier("TestQuestion2",
                new LineNumber(2)), new LineNumber(1));

        List<Statement> statements = new ArrayList<>();

        statements.add(q1);

        Form form = new Form(new Identifier("TestForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();

        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getErrors().get(0).getMessage(),
                "ERROR: Question TestQuestion2 at line 2 is undefined.");
    }

    @Test
    public void testDuplicateIdentifierWarning() {

        ComputedQuestion q1 = new ComputedQuestion(new Identifier("TestQuestion1", new LineNumber(1)),
                "Question text 1", new BooleanType(), new MyBoolean(false,
                new LineNumber(1)), new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("TestQuestion1", new LineNumber(2)),
                "Question text 2", new BooleanType(), new MyBoolean(false,
                new LineNumber(2)), new LineNumber(2));

        List<Statement> statements = new ArrayList<>();

        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("TestForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();

        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getWarnings().get(0).getMessage(),
                "WARNING: Question TestQuestion1 at line 2 has a duplicate identifier with the same type.");
    }

    @Test
    public void testDuplicateLabelWarning() {

        ComputedQuestion q1 = new ComputedQuestion(new Identifier("TestQuestion1", new LineNumber(1)),
                "Question text", new BooleanType(), new MyBoolean(false,
                new LineNumber(1)), new LineNumber(1));

        ComputedQuestion q2 = new ComputedQuestion(new Identifier("TestQuestion2", new LineNumber(2)),
                "Question text", new BooleanType(), new MyBoolean(false,
                new LineNumber(2)), new LineNumber(2));

        List<Statement> statements = new ArrayList<>();

        statements.add(q1);
        statements.add(q2);

        Form form = new Form(new Identifier("TestForm", new LineNumber(1)), statements,
                new LineNumber(1));

        ValueData questionData = new ValueData();

        SemanticChecker semanticChecker = new SemanticChecker(form, questionData);
        MessageData messages = semanticChecker.getMessages();

        Assert.assertEquals(messages.getWarnings().get(0).getMessage(),
                "WARNING: Question TestQuestion2 at line 2 has a duplicate label: Question text.");
    }
}
