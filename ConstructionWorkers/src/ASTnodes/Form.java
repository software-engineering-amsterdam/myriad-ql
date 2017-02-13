package ASTnodes;

/**
 * Created by LGGX on 09-Feb-17.
 */
import ASTnodes.expressions.literals.IDENTIFIER;
import ASTnodes.sections.Section;
import ASTnodes.visitors.AllVisitors;

import java.util.List;

public class Form extends Node {

    private final IDENTIFIER identifier;
    private final List<Section> sections;

    public Form(IDENTIFIER identifier, List<Section> sections, CodeLocation location) {
        super(location);
        this.identifier = identifier;
        this.sections = sections;
    }

    public IDENTIFIER getIdentifier() {
        return identifier;
    }

    public List<Section> getSections() {
        return sections;
    }

    public <T, U> T accept(AllVisitors<T, U> visitor, U context) {
        return visitor.visit(this, context);
    }

}
