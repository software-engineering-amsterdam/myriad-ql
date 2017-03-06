package semantic;
import java.util.List;

import QL.Warning;
import ast.Form;

public class TypeChecker {

    public List<Warning> analyze(Form form) {
        Environment environment = new Environment();
        QuestionVisitor QVisitor = new QuestionVisitor(environment);
        QVisitor.visit(form);

        ExpressionVisitor expressionVisitor = new ExpressionVisitor(environment);
        expressionVisitor.visit(form);
        return environment.getWarnings();
    }

}
