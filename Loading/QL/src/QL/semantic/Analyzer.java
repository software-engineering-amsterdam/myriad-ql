package QL.semantic;
import QL.semantic.Environment;
import QL.Faults;
import QL.ReferenceTable;
import QL.ast.Form;

public class Analyzer {
		
	private Environment environment;
	
	public Analyzer() {
		this.environment = new Environment();		
	}

    public ReferenceTable analyze(Form form) {
        VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
        verifyQuestions.visit(form);

        VerifyExpressions verifyExpressions = new VerifyExpressions(environment);
        verifyExpressions.visit(form);

        CheckCyclicDependencies cyclicVisitor = new CheckCyclicDependencies(environment);
        cyclicVisitor.visit(form);
        
        return environment.getReferenceTable();
    }

    
    public Faults getFaults() {
    	return environment.getFaults();
    }
}
