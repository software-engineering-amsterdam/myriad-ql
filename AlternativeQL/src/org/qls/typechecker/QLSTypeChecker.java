package org.qls.typechecker;

import org.ql.typechecker.SymbolTable;
import org.ql.typechecker.issues.IssuesStorage;
import org.qls.ast.StyleSheet;

public class QLSTypeChecker {
    private final IssuesStorage issuesStorage;
    private QLSQuestionVisitor qlsQuestionReferenceVisitor;

    public QLSTypeChecker() {
        issuesStorage = new IssuesStorage();
    }

    public IssuesStorage checkStyleSheet(StyleSheet styleSheet, SymbolTable symbolTable) {
        qlsQuestionReferenceVisitor = new QLSQuestionVisitor(issuesStorage, styleSheet, symbolTable);

        return issuesStorage;
    }
}
