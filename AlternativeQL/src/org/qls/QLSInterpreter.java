package org.qls;

import org.ql.ast.QLASTBuilder;
import org.ql.ast.Form;
import org.ql.gui.QLApplication;
import org.ql.io.QLFile;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;
import org.qls.io.QLSFile;
import org.qls.ast.QLSASTBuilder;
import org.qls.typechecker.QLSTypeChecker;

import java.io.IOException;

public class QLSInterpreter {
    private final QLASTBuilder qlAstBuilder = new QLASTBuilder();
    private final QLSASTBuilder qlsAstBuilder = new QLSASTBuilder();
    private final QLSTypeChecker qlsTypeChecker = new QLSTypeChecker();

    public void interpret(QLFile qlFileLocation, QLSFile qlsFileLocation) throws IOException {
        Form form = qlAstBuilder.buildAST(qlFileLocation.openStream());
        StyleSheet styleSheet = qlsAstBuilder.buildAST(qlsFileLocation.openStream());

        IssuesStorage issues  = qlsTypeChecker.checkStyleSheet(form, styleSheet);
        displayErrors(issues);

        if (issues.hasErrors()) {
            System.exit(1);
        }

        issues.getWarnings().forEach(this::printIssue);

        QLApplication.run(form);
    }

    private void displayErrors(IssuesStorage issues) {
        issues.getErrors().forEach(this::printIssue);
    }

    private void printIssue(Issue issue) {
        System.out.println(issue.getMessage());
    }
}
