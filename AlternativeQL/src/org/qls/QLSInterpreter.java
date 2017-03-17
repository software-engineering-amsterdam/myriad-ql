package org.qls;

import org.ql.ast.Form;
import org.ql.ast.Node;
import org.ql.gui.QLApplication;
import org.ql.io.QLFile;
import org.ql.parser.ASTBuilder;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.TypeChecker;
import org.ql.typechecker.issues.Issue;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;
import org.qls.grammar.QLSVisitor;
import org.qls.io.QLSFile;
import org.qls.parser.Parser;
import org.qls.parser.QLSASTBuilder;

import java.io.IOException;

public class QLSInterpreter {
    private final ASTBuilder qlAstBuilder = new ASTBuilder();
    private final TypeChecker qlTypeChecker = new TypeChecker();
    private final QLSASTBuilder qlsAstBuilder = new QLSASTBuilder();

    public void interpret(QLFile qlFileLocation, QLSFile qlsFileLocation) throws IOException {

        Form formAST = qlAstBuilder.buildAST(qlFileLocation.openStream());
        StyleSheet styleSheet = qlsAstBuilder.buildAST(qlsFileLocation.openStream());

        SymbolTable symbolTable = new SymbolTable();
        IssuesStorage issues = qlTypeChecker.checkForm(formAST, symbolTable);

        if (issues.hasErrors()) {
            issues.getErrors().forEach(this::printIssue);
            System.exit(1);
        }

        // TODO: Check QLS typechecker issues here

        issues.getWarnings().forEach(this::printIssue);

        QLApplication.run(formAST);
    }

    private void printIssue(Issue issue) {
        System.out.println(issue.getFullMessage());
    }
}
