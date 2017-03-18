package org.ql;

import org.ql.ast.Form;
import org.ql.gui.QLApplication;
import org.ql.io.QLFile;
import org.ql.ast.QLASTBuilder;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.QLTypeChecker;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;
import java.io.IOException;

public class QLInterpreter {
    private final QLASTBuilder astBuilder = new QLASTBuilder();
    private final QLTypeChecker typeChecker = new QLTypeChecker();

    /**
     * Attempts to interpret a QL program
     *
     * Initializes AST building, type-checking and running a GUI application
     *
     * @param file A correct QL file value object holding input stream from the source file
     * @throws IOException
     */
    public void interpret(QLFile file) throws IOException {

        Form formAST = astBuilder.buildAST(file.openStream());

        IssuesStorage issues = typeChecker.checkForm(formAST, new SymbolTable());

        if (issues.hasErrors()) {
            issues.getErrors().forEach(this::printIssue);
            System.exit(1);
        }

        issues.getWarnings().forEach(this::printIssue);

        QLApplication.run(formAST);
    }

    private void printIssue(Issue issue) {
        System.out.println(issue.getFullMessage());
    }
}
