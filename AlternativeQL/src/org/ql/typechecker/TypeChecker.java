package org.ql.typechecker;

import org.ql.ast.Form;
import org.ql.typechecker.issues.*;
import org.ql.typechecker.visitor.CircularDependencyVisitor;
import org.ql.typechecker.visitor.QuestionsVisitor;
import org.ql.typechecker.visitor.TypeMismatchVisitor;

public class TypeChecker {

    private final Form form;

    private final QuestionsVisitor questionsVisitor;
    private final TypeMismatchVisitor typeMismatchVisitor;
    private final CircularDependencyVisitor circularDependenciesVisitor;
    private final IssuesStorage issuesStorage;

    public TypeChecker(Form form) {
        this.form = form;
        this.issuesStorage = new IssuesStorage();

        questionsVisitor = new QuestionsVisitor(issuesStorage);
        typeMismatchVisitor = new TypeMismatchVisitor(issuesStorage);
        circularDependenciesVisitor = new CircularDependencyVisitor(issuesStorage);
    }

    public IssuesStorage checkForm() {
        SymbolTable symbolTable = new SymbolTable();
        questionsVisitor.visitForm(form, symbolTable);
        typeMismatchVisitor.visitForm(form, symbolTable);
        circularDependenciesVisitor.visitForm(form, null);

        return issuesStorage;
    }
}
