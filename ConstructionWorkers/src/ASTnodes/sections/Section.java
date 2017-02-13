package ASTnodes.sections;

/**
 * Created by LGGX on 09-Feb-17.
 */
import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

public abstract class Section extends Node {

    public Section(CodeLocation location) {
        super(location);
    }

    public abstract <T, U> T accept(AllVisitors<T, U> visitor, U context);

}
