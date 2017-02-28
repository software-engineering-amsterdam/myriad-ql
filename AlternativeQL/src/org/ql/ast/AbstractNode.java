package org.ql.ast;

public abstract class AbstractNode implements Node {
    private Metadata metadata;

    public AbstractNode setMetadata(Metadata metadata) {
        this.metadata = metadata;

        return this;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
