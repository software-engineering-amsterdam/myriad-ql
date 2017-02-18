package org.lemonade;

import java.util.ArrayList;
import java.util.List;

import org.lemonade.expression.Expression;

public class Conditional extends Block{

    private Expression condition;
    private List<Block> blocks;

    //How are we going to validate expressions referring to questions declared above?
    public Conditional (Expression expr, List<Block> blocks) {
        super();
        this.condition = expr;
        this.blocks = blocks;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Expression getCondition() {
        return condition;
    }

    //Validate the org.lemonade.expression and test whether it can be reduced to a bool.
}
