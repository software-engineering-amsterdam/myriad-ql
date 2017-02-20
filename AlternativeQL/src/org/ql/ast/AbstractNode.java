package org.ql.ast;

public abstract class AbstractNode implements Node {
    protected Metadata metadata;

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
