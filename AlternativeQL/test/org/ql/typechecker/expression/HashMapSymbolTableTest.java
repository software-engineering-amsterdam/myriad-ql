package org.ql.typechecker.expression;

import org.junit.Test;
import org.ql.ast.Identifier;
import org.ql.ast.type.StringType;
import org.ql.symbol_table.HashMapSymbolTable;
import org.ql.symbol_table.SymbolTable;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class HashMapSymbolTableTest {
    @Test
    public void shouldPutIdentifierWhenPutExecuted() {
        SymbolTable symbolTable = new HashMapSymbolTable();

        symbolTable.put(new Identifier("example"), new StringType());

        assertTrue(symbolTable.has(new Identifier("example")));
        assertSame(symbolTable.size(), 1);
    }

    @Test
    public void shouldGetIdentifierWhenGetExecuted() {
        SymbolTable symbolTable = new HashMapSymbolTable();
        Identifier identifier = new Identifier("example");
        StringType actualType = new StringType();

        symbolTable.put(identifier, actualType);

        assertSame(symbolTable.get(identifier), actualType);
    }

    @Test
    public void shouldContainKeyWhenContainsKeyExecuted() {
        SymbolTable symbolTable = new HashMapSymbolTable();
        Identifier identifier = new Identifier("example");
        StringType actualType = new StringType();

        symbolTable.put(identifier, actualType);

        assertTrue(symbolTable.has(identifier));
    }
}
