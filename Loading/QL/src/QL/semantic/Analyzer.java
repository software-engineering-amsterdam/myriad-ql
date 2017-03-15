package QL.semantic;
import java.util.Map;

import QL.Faults;
import QL.ast.Form;
import QL.ast.type.Type;

public class Analyzer {
	
	private Environment environment;

    public Faults analyze(Form form) {
        this.environment = new Environment();
        VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
        verifyQuestions.visit(form);

        VerifyExpressions verifyExpressions = new VerifyExpressions(environment);
        verifyExpressions.visit(form);

        CheckCyclicDependencies cyclicVisitor = new CheckCyclicDependencies(environment);
        cyclicVisitor.visit(form);

        return environment.getFaults();

    }
    
    public Map<String, Type> getVariableTypes() {
    	return environment.getVariableTypes();
    }

}
