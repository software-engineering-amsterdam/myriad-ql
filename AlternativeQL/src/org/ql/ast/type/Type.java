package org.ql.ast.type;

import org.ql.ast.Node;

public abstract class Type extends Node {

    public boolean isBoolean() {
        return false;
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isDate() {
        return false;
    }
}
