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

    private StatementVisitor sVisitor;
    private Form astRoot;

    public QLTreeChecker(Form form) {
        this.astRoot = form;
        this.sVisitor = new StatementVisitor();
    }

    public void runChecks() {
        for (Statement statement : astRoot.getStatements()) {
            statement.accept(sVisitor);
        }
    }

}
