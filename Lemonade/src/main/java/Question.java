/**
 * Created by dimitribelfor on 07/02/2017.
 */
public class Question extends ASTNode {
    private String identifier;
    private String label;
    private Types typeSpecifier;

    public Question (String identifier, String label, Types typeSpecifier, int lineNo) {
        super(lineNo);
        this.identifier = identifier;
        this.label = label;
        this.typeSpecifier = typeSpecifier;
    }
}
