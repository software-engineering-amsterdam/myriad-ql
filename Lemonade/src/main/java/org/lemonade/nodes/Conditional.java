package org.lemonade.nodes;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.visitors.BlockVisitor;

import java.util.List;

public class Conditional extends Block {

    private Expression condition;
    private List<Block> blocks;

    //How are we going to validate expressions referring to questions declared above?
    public Conditional(Expression expr, List<Block> blocks) {
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

    @Override
    public <T> T accept(BlockVisitor<T> visitor) {
        return visitor.visit(this);
    }

    //Validate the org.lemonade.nodes.expressions and test whether it can be reduced to a bool.
}
