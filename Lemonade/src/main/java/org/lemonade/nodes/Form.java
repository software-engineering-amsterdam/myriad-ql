package org.lemonade.nodes;

import java.util.List;

import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.visitors.interfaces.BaseVisitor;

//Maybe everything inherits from the org.lemonade.nodes.ASTNode class so we can easily walk through
//the constructed tree?
public class Form extends ASTNode{
    private IdentifierLiteral identifier;
    private List<Body> bodies;

    public Form(IdentifierLiteral identifier, List<Body> bodies) {
        super();
        this.identifier = identifier;
        this.bodies = bodies;
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public IdentifierLiteral getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return this.identifier.getValue();
    }

    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
