package org.qls.typechecker;

import org.ql.ast.Form;
import org.ql.typechecker.QLTypeChecker;
import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;

public class QLSTypeChecker {
    private final QLTypeChecker qlTypeChecker;

    public QLSTypeChecker() {
        qlTypeChecker = new QLTypeChecker();
    }

    public IssuesStorage checkStyleSheet(Form formAST, StyleSheet styleSheet) {

        SymbolTable symbolTable = new SymbolTable();
        IssuesStorage issuesStorage = qlTypeChecker.checkForm(formAST, symbolTable);

        QLSQuestionVisitor qlsQuestionReferenceVisitor = new QLSQuestionVisitor(issuesStorage);

        return issuesStorage;
    }
}
