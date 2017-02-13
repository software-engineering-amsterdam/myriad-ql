import org.uva.taxfree.GUI.QuestionForm;

public class QuestionFormTest {
    public static void main(String[] args)
    {
        Node simpleRoot = new FormNode("SampleForm", null);
        AST simpleAst = new AST(simpleRoot);
        QuestionForm form = new QuestionForm();
        form.generateForm(simpleAst);
    }
}