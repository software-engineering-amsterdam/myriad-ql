package semantic;
import ast.Form;
import ast.atom.Atom;

import java.util.Map;

public class TypeChecker {

    public Environment analyze(Form form) {
        Environment environment = new Environment();
        QuestionVisitor QVisitor = new QuestionVisitor(environment);
        QVisitor.visit(form);
        // TODO if you forget this statement you continue working with the old environment

        System.out.println("!!!!!!");
        environment.variableExists("Name1");
        ExpressionVisitor expressionVisitor = new ExpressionVisitor(environment);
        expressionVisitor.visit(form);
        return expressionVisitor.getEnvironment();

    }

}
