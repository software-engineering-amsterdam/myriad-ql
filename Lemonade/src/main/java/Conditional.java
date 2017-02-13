/**
 * Created by dimitribelfor on 07/02/2017.
 */
public class Conditional extends ASTNode{

    private Expression expr;
    private List<Question> question;

    //How are we going to validate expressions referring to questions declared above?
    public Conditional (Expression expr, Question question) {
        this.expr = expr;
        this.question = question;
    }
    public Question getQuestion() {
        return question;
    }
    public Expression getExpr() {
        return expr;
    }

    //Validate the expression and test whether it can be reduced to a bool.
}
