package org.ql.ast;

public abstract class AbstractNode implements Node {
    protected Metadata metadata;

    public Metadata getMetadata() {
        return metadata;
    }
}
