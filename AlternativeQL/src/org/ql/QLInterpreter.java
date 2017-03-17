package org.ql;

import org.ql.ast.Form;
import org.ql.gui.QLApplication;
import org.ql.io.QLFile;
import org.ql.parser.ASTBuilder;
import org.ql.typechecker.TypeChecker;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;
import java.io.IOException;

public class QLInterpreter {
    private final ASTBuilder astBuilder = new ASTBuilder();
    private final TypeChecker typeChecker = new TypeChecker();

    public void interpret(QLFile fileLocation) throws IOException {

        Form formAST = astBuilder.buildAST(fileLocation.openStream());

        IssuesStorage issues = typeChecker.checkForm(formAST);

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
