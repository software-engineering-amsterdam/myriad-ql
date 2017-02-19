package org.lemonade.nodes;

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

    @Override
    public String toString() {
        return this.identifier;
    }

    //TODO Visitor??
}
