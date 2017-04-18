package org.ql.typechecker;

import org.junit.Test;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.type.StringType;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SymbolTableTest {
    @Test
    public void shouldPutIdentifierWhenPutExecuted() {
        SymbolTable symbolTable = new SymbolTable();

        symbolTable.declare(new Identifier("example"), new StringType());

        assertTrue(symbolTable.isDeclared(new Identifier("example")));
        assertSame(symbolTable.size(), 1);
    }

    @Test
    public void shouldGetIdentifierWhenGetExecuted() {
        SymbolTable symbolTable = new SymbolTable();
        Identifier identifier = new Identifier("example");
        StringType actualType = new StringType();

        symbolTable.declare(identifier, actualType);

        assertSame(symbolTable.lookup(identifier), actualType);
    }

    @Test
    public void shouldContainKeyWhenContainsKeyExecuted() {
        SymbolTable symbolTable = new SymbolTable();
        Identifier identifier = new Identifier("example");
        StringType actualType = new StringType();

        symbolTable.declare(identifier, actualType);

        assertTrue(symbolTable.isDeclared(identifier));
    }
}
