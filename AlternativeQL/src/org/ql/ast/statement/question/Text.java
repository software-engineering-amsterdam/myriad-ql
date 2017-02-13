package org.ql.ast.statement.question;

import org.ql.ast.Node;

public class Text implements Node {
    private final String questionText;

    public Text(String questionText) {
        this.questionText = questionText;
    }

    public String toString() {
        return questionText;
    }
}
