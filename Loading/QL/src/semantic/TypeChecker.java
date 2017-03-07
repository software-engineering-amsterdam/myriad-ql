package semantic;
import java.util.List;
import java.util.Map;

import QL.Warning;
import ast.Form;
import ast.type.Type;

public class TypeChecker {
	
	Environment environment;

    public List<Warning> analyze(Form form) {
        this.environment = new Environment();
        QuestionVisitor QVisitor = new QuestionVisitor(environment);
        QVisitor.visit(form);

        ExpressionVisitor expressionVisitor = new ExpressionVisitor(environment);
        expressionVisitor.visit(form);
        return environment.getWarnings();
    }
    
    public Map<String, Type> getVariableTypes() {
    	return environment.getVariableTypes();
    }

}
