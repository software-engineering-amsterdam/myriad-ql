package org.lemonade.nodes;


import org.lemonade.visitors.ASTVisitor;

import java.util.List;

//Maybe everything inherits from the org.lemonade.nodes.ASTNode class so we can easily walk through
//the constructed tree?
public class Form implements ASTNode {
    private String identifier;
    private List<Body> bodies;

    public Form(String identifier, List<Body> bodies) {
        super();
        this.identifier = identifier;
        this.bodies = bodies;
    }

    public List<Body> getBodies() {
        return bodies;
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
