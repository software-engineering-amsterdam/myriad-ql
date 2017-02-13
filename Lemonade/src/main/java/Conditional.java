/**
 * Created by dimitribelfor on 07/02/2017.
 */
public class Conditional extends ASTNode{

    private Expression condition;
    private List<Question> question;

    //How are we going to validate expressions referring to questions declared above?
    public Conditional (Expression expr, Question question) {
        this.condition = expr;
        this.question = question;
    }
    public Question getQuestion() {
        return question;
    }
    public Expression getCondition() {
        return condition;
    }

    //Validate the expression and test whether it can be reduced to a bool.
}
