package ASTnodes.sections;

/**
 * Created by LGGX on 10-Feb-17.
 */
import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.IDENTIFIER;
import ASTnodes.visitors.AllVisitors;

import ASTnodes.types.Type;

public class Question extends Section {

    private final IDENTIFIER identifier;
    private final String label;
    private final Type type;

    public Question(IDENTIFIER identifier, String label, Type type, CodeLocation location) {
        super(location);
        this.identifier = identifier;
        this.label = label;
        this.type = type;
    }

    public IDENTIFIER getIdentifier() {
        return identifier;
    }

    public String getLabel() {
        return label;
    }

    public Type getType() {
        return type;
    }
    /*
    public Value getDefaultValue() {
        return type.getDefaultValue();
    }
    */
    @Override
    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Question)) {
            return false;
        }
        Question q2 = (ExpressionQuestion) obj;
        return identifier.equals(q2.getIdentifier());
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

}