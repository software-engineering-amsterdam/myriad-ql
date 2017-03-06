package org.ql.typechecker.visitor;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.expression.Parameter;
import org.ql.typechecker.SymbolTable;

import static org.junit.Assert.*;

public class UndefinedSymbolVisitorTest {
    @Test
    public void shouldGiveUndefinedSymbolErrorWhenIdentifierDoesNotExist() {
        UndefinedSymbolVisitor visitor = new UndefinedSymbolVisitor(new SymbolTable());

        visitor.visitParameter(new Parameter(new Identifier("example")), null);

        assertEquals(1, visitor.getErrors().size());
    }
}
