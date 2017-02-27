package org.ql.typechecker.messages;

import org.junit.Test;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.StringType;
import org.ql.typechecker.expression.TypeMismatchException;

import static org.junit.Assert.*;

public class TypeCheckMessagesTest {
    @Test
    public void shouldAddTypeMismatchErrorWithoutMetadata() {
        MessageBag messageBag = new TypeCheckMessages();
        messageBag.addError(new TypeMismatchException(new BooleanType(), new StringType()));

        assertEquals(1, messageBag.getErrors().size());
        assertEquals("Type mismatch: expected boolean, but got string", messageBag.getErrors().get(0));
    }
}