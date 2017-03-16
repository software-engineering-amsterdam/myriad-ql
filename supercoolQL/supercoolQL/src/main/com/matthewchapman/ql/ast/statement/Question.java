package com.matthewchapman.ql.ast.statement;

import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.validation.visitor.QLVisitor;

/**
 * Created by matt on 20/02/2017.
 * <p>
 * Base question class, contains an identifier, type and a possibly calculated value.
 */
public class Question extends Statement {

    private final String name;
    private final Type type;
    private final String text;

    public Question(String name, String text, Type type, int line, int column) {
        this.name = name;
        this.text = text;
        this.type = type;
        this.setColumn(column);
        this.setLine(line);
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return "\t" + this.text + "\n\t" + this.name + ":" + this.type + "\n";
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }

}
