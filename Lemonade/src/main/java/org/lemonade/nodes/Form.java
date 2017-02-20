package org.lemonade.nodes;


import org.lemonade.visitors.ASTVisitor;

import java.util.List;

//Maybe everything inherits from the org.lemonade.nodes.ASTNode class so we can easily walk through
//the constructed tree?
public class Form extends ASTNode {
    private String identifier;
    private List<Block> blocks;

    public Form(String identifier, List<Block> blocks) {
        super();
        this.identifier = identifier;
        this.blocks = blocks;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return this.identifier;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
