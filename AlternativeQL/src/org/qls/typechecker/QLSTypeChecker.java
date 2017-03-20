package org.qls.typechecker;

import org.ql.ast.Form;
import org.ql.typechecker.QLTypeChecker;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;

public class QLSTypeChecker {
    private final QLTypeChecker qlTypeChecker;
    private final QLSQuestionVisitor qlsQuestionVisitor;

    public QLSTypeChecker() {
        qlTypeChecker = new QLTypeChecker();
        qlsQuestionVisitor = new QLSQuestionVisitor();
    }

    public IssuesStorage checkStyleSheet(Form formAST, StyleSheet styleSheet) {
        SymbolTable symbolTable = new SymbolTable();
        IssuesStorage issuesStorage = qlTypeChecker.checkForm(formAST, symbolTable);

        qlsQuestionVisitor.visitStyleSheet(styleSheet, issuesStorage, symbolTable);

        return issuesStorage;
    }
}
