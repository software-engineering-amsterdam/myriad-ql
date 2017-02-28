package semantic;
import ast.Form;
import ast.atom.Atom;

import java.util.Map;

public class TypeChecker {

    public Environment analyze(Form form) {
        Environment environment = new Environment();
        QuestionVisitor QVisitor = new QuestionVisitor(environment);
        QVisitor.visit(form);

        ExpressionVisitor expressionVisitor = new ExpressionVisitor(environment);
        expressionVisitor.visit(form);
        return environment;
    }

}
