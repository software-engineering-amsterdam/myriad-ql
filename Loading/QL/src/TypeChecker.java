import ast.Form;
import ast.atom.Atom;

import java.util.Map;

public class TypeChecker {

    public static void main(Form form) {
        Environment environment = new Environment();
        QuestionVisitor QVisitor = new QuestionVisitor(environment);
        QVisitor.visit(form);
        environment = QVisitor.getEnvironment(); // TODO if you forget this statement you continue working with the old environment

        Map<String, Atom> answers = environment.getAnswers();

        for (String answer : answers.keySet()) {
            System.out.println("Question: " + answer);
        }

        ExpressionVisitor expressionVisitor = new ExpressionVisitor(environment);
        expressionVisitor.visit(form);
        environment = expressionVisitor.getEnvironment();

    }

}
