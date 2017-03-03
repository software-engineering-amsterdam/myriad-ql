package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;

/**
 * Created by matt on 27/02/2017.
 *
 * Type checker for the QL AST. Previously implemented the Visitor pattern, now instantiates visitors
 * within itself to allow better encapsulation.
 */
public class QLTreeChecker {

    private StatementVisitor statementVisitor;

    private Form astRoot;

    public QLTreeChecker(Form form) {
        this.astRoot = form;
        this.statementVisitor = new StatementVisitor();
    }

    public void runChecks() {
        for (Statement statement : astRoot.getStatements()) {
            statement.accept(statementVisitor);
        }
    }

}
