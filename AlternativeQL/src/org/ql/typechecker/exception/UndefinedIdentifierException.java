package org.ql.typechecker.exception;


import org.ql.ast.Identifier;

public class UndefinedIdentifierException extends RuntimeException implements TypeError {
    private final Identifier identifier;

    public UndefinedIdentifierException(Identifier identifier) {
        this.identifier = identifier;
    }
}