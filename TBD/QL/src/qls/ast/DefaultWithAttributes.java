package qls.ast;

/**
 * Created by rico on 7-3-17.
 */
public class DefaultWithAttributes extends ASTNode {
    private final String type;
    private final DefaultAttributes attributes;

    public DefaultWithAttributes(String type, DefaultAttributes attributes, int rowNumber) {
        super(rowNumber);
        this.type = type;
        this.attributes = attributes;
    }

    public DefaultAttributes getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }
}
