package org.ql.typechecker.messages;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.Metadata;
import org.ql.ast.Statement;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.StringType;
import org.ql.typechecker.expression.TypeMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TypeCheckMessagesTest {
    @Test
    public void shouldAddTypeMismatchErrorWithoutMetadata() {
        MessageBag messageBag = new TypeCheckMessages();
        messageBag.addError(new TypeMismatchException(new BooleanType(), new StringType()));

        assertEquals(1, messageBag.getErrors().size());
        assertTrue(messageBag.hasErrors());
        assertEquals("Type mismatch: expected boolean, but got string", messageBag.getErrors().get(0));
    }

    @Test
    public void shouldAddTypeMismatchErrorWithMetadata() {
        MessageBag messageBag = new TypeCheckMessages();
        StringType actualType = new StringType();
        actualType.setMetadata(new Metadata(12, 34));
        messageBag.addError(new TypeMismatchException(new BooleanType(), actualType));

        assertEquals(1, messageBag.getErrors().size());
        assertTrue(messageBag.hasErrors());
        assertEquals("Type mismatch: expected boolean, but got string on line 12:34", messageBag.getErrors().get(0));
    }

    @Test
    public void shouldAddStringErrorWithNodeWithoutMetadata() {
        MessageBag messageBag = new TypeCheckMessages();
        messageBag.addError("Strange error", mock(Statement.class));

        assertEquals(1, messageBag.getErrors().size());
        assertEquals("Strange error", messageBag.getErrors().get(0));
    }

    @Test
    public void shouldAddStringErrorWithNodeWithMetadata() {
        MessageBag messageBag = new TypeCheckMessages();
        Identifier actualNode = new Identifier("haha");
        actualNode.setMetadata(new Metadata(12, 34));
        messageBag.addError("Strange error", actualNode);

        assertEquals(1, messageBag.getErrors().size());
        assertEquals("Strange error on line 12:34", messageBag.getErrors().get(0));
    }

    @Test
    public void shouldAddStringError() {
        MessageBag messageBag = new TypeCheckMessages();
        messageBag.addError("Strange error");

        assertEquals(1, messageBag.getErrors().size());
        assertEquals("Strange error", messageBag.getErrors().get(0));
    }
}