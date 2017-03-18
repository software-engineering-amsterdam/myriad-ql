package org.qls;

import org.ql.ast.QLASTBuilder;
import org.ql.ast.Form;
import org.ql.gui.QLApplication;
import org.ql.io.QLFile;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.QLTypeChecker;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;
import org.qls.io.QLSFile;
import org.qls.parser.QLSASTBuilder;
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
        checkErrors(issues);

        issues.getWarnings().forEach(this::printIssue);

        QLApplication.run(form);
    }

    private void checkErrors(IssuesStorage issues) {
        if (issues.hasErrors()) {
            issues.getErrors().forEach(this::printIssue);
            System.exit(1);
        }
    }

    private void printIssue(Issue issue) {
        System.out.println(issue.getMessage());
    }
}
