package org.ql.typechecker.messages;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.SourceLocation;
import org.ql.ast.Statement;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.StringType;
import org.ql.typechecker.Messages;
import org.ql.typechecker.expression.TypeMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class MessagesTest {
    @Test
    public void shouldAddTypeMismatchErrorWithoutMetadata() {
        Messages messages = new Messages();
        messages.addError(new TypeMismatchException(new BooleanType(), new StringType()));

        assertEquals(1, messages.getErrors().size());
        assertTrue(messages.hasErrors());
        assertEquals("Type mismatch: expected boolean, but got string", messages.getErrors().get(0));
    }

    @Test
    public void shouldAddTypeMismatchErrorWithMetadata() {
        Messages messages = new Messages();
        StringType actualType = new StringType();
        actualType.setSourceLocation(new SourceLocation(12, 34));
        messages.addError(new TypeMismatchException(new BooleanType(), actualType));

        assertEquals(1, messages.getErrors().size());
        assertTrue(messages.hasErrors());
        assertEquals("Type mismatch: expected boolean, but got string on line 12:34", messages.getErrors().get(0));
    }

    @Test
    public void shouldAddStringErrorWithNodeWithoutMetadata() {
        Messages messages = new Messages();
        messages.addError("Strange error", mock(Statement.class));

        assertEquals(1, messages.getErrors().size());
        assertEquals("Strange error", messages.getErrors().get(0));
    }

    @Test
    public void shouldAddStringErrorWithNodeWithMetadata() {
        Messages messages = new Messages();
        Identifier actualNode = new Identifier("haha");
        actualNode.setSourceLocation(new SourceLocation(12, 34));
        messages.addError("Strange error", actualNode);

        assertEquals(1, messages.getErrors().size());
        assertEquals("Strange error on line 12:34", messages.getErrors().get(0));
    }

    @Test
    public void shouldAddStringError() {
        Messages messages = new Messages();
        messages.addError("Strange error");

        assertEquals(1, messages.getErrors().size());
        assertEquals("Strange error", messages.getErrors().get(0));
    }
}