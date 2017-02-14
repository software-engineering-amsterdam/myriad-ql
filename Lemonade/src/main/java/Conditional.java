import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitribelfor on 07/02/2017.
 */
public class Conditional extends ASTNode{

    private Expression condition;
    private List<Question> questions;

    //How are we going to validate expressions referring to questions declared above?
    public Conditional (Expression expr, int lineNo) {
        super(lineNo);
        this.condition = expr;
        this.questions = new ArrayList<Question>();
    }
    public List<Question> getQuestions() {
        return questions;
    }

    public Expression getCondition() {
        return condition;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    //Validate the expression and test whether it can be reduced to a bool.
}
