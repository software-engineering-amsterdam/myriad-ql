package org.qls.typechecker;

import org.ql.ast.form.Form;
import org.ql.typechecker.QLTypeChecker;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;

public class QLSTypeChecker {
    private final QLTypeChecker qlTypeChecker = new QLTypeChecker();
    private final QLSQuestionVisitor qlsQuestionVisitor = new QLSQuestionVisitor();
    private final SymbolTable symbolTable = new SymbolTable();

    public IssuesStorage checkStyleSheet(Form formAST, StyleSheet styleSheet) {
        IssuesStorage issuesStorage = qlTypeChecker.checkForm(formAST, symbolTable);

        qlsQuestionVisitor.visitStyleSheet(styleSheet, issuesStorage, symbolTable);

        return issuesStorage;
    }
}
