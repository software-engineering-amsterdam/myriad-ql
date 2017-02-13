package ASTnodes.sections;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;
import ASTnodes.expressions.Expression;

import java.util.List;

/**
 * Created by LGGX on 09-Feb-17.
 */
public class IfStatement extends Section {

    private final Expression expression;
    private final List<Section> sections;

    public IfStatement(Expression expression, List<Section> sections, CodeLocation location) {
        super(location);
        this.expression = expression;
        this.sections = sections;
    }

    public Expression getExpression() {
        return expression;
    }

    public List<Section> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        String string = "\n if (" + this.expression.toString() + ") {\n";

        for (Section section : this.sections)
            string += (section.toString() + "\n");
        string += "\n}";

        return string;
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }

}
