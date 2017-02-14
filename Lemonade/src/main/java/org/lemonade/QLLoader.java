package org.lemonade;

import java.util.Stack;

public class QLLoader extends QLBaseListener {
    private Stack<Object> stack = new Stack<>();

    @Override
    public void exitForm(QLParser.FormContext ctx) {
        String id = ctx.identifier().getText();
    }

    public void exitExpression() {

    }

}

