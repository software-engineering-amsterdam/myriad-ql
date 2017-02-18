package org.lemonade;

import org.lemonade.expression.Type;

public class Question extends Block {
    private String identifier;
    private String label;
    private Type type;

    public Question(String identifier, String label, Type type) {
        super();
        this.identifier = identifier;
        this.label = label;
        this.type = type;
    }
}
