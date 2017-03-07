package semantic;
import java.util.List;
import java.util.Map;

import QL.Fault;
import QL.Faults;
import ast.Form;
import ast.type.Type;

public class TypeChecker {
	
	Environment environment;

    public Faults analyze(Form form) {
        this.environment = new Environment();
        QuestionVisitor QVisitor = new QuestionVisitor(environment);
        QVisitor.visit(form);

        ExpressionVisitor expressionVisitor = new ExpressionVisitor(environment);
        expressionVisitor.visit(form);

        CyclicDependenciesVisitor cyclicDependenciesVisitor = new CyclicDependenciesVisitor(environment);
        cyclicDependenciesVisitor.visit(form);

        return environment.getFaults();

    }
    
    public Map<String, Type> getVariableTypes() {
    	return environment.getVariableTypes();
    }

}
