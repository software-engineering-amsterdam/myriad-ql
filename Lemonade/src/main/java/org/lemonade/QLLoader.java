package org.lemonade;

import java.util.Stack;

public class QLLoader extends org.lemonade.QLBaseListener {
    private Stack<Object> stack = new Stack<>();

    @Override
    public void exitForm(org.lemonade.QLParser.FormContext ctx) {
        String id = ctx.identifier().getText();
    }

    public void exitExpression() {

    }

}

