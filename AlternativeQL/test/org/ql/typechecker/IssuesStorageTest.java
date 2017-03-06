package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.SourceLocation;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.StringType;
import org.ql.typechecker.issues.IssuesStorage;
import org.ql.typechecker.issues.error.TypeMismatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class IssuesStorageTest {
    @Test
    public void shouldAddTypeMismatchErrorWithoutMetadata() {
        IssuesStorage issuesStorage = new IssuesStorage();
        issuesStorage.addError(new TypeMismatch(new BooleanType(), new StringType()));

        assertEquals(1, issuesStorage.getErrors().size());
        assertTrue(issuesStorage.hasErrors());
        assertEquals("Type mismatch: expected boolean, but got string", issuesStorage.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldAddTypeMismatchErrorWithLoc() {
        IssuesStorage issuesStorage = new IssuesStorage();
        StringType actualType = new StringType();
        actualType.setSourceLocation(new SourceLocation(12, 34));
        issuesStorage.addError(new TypeMismatch(new BooleanType(), actualType));

        assertEquals(1, issuesStorage.getErrors().size());
        assertTrue(issuesStorage.hasErrors());
        assertEquals("Type mismatch: expected boolean, but got string on line 12:34", issuesStorage.getErrors().get(0).getFullMessage());
    }
}