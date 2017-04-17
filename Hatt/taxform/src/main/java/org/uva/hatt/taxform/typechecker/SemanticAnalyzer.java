package org.uva.hatt.taxform.typechecker;

import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.typechecker.messages.Message;

public class SemanticAnalyzer {

    private final Message message;
    private final TypeChecker typeChecker;
    private final CircularDependencyChecker circularDependencyChecker;

    public SemanticAnalyzer() {
        this.message = new Message();
        this.typeChecker = new TypeChecker(message);
        this.circularDependencyChecker = new CircularDependencyChecker(message);
    }

    public Message analyze(Form form) {
        typeChecker.visit(form);
        circularDependencyChecker.visit(form);

        return message;
    }
}
