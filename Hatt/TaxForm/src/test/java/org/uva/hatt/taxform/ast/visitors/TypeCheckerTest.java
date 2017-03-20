package org.uva.hatt.taxform.ast.visitors;

import org.junit.Test;
import org.uva.hatt.taxform.ast.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.items.Item;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.visitors.exceptionHandler.ExceptionHandler;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class TypeCheckerTest {
    @Test
    public void testDuplicateLabel() throws IOException {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"Same label question\" hasSoldHouse: string \"Same label question\" sellingPrice: money }";
        Form form = ASTGenerator.getForm(qlForm);
        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);
        typeChecker.visit(q1);
        typeChecker.visit(q2);
        assertEquals("DUPLICATE_LABEL", exceptionHandler.getErrors().get(0).getMessage());
    }

    @Test
    public void testDuplicteDeclaration() throws IOException {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"question 1\" var: string \"question 2\" var: money }";
        Form form = ASTGenerator.getForm(qlForm);
        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);
        typeChecker.visit(q1);
        typeChecker.visit(q2);
        assertEquals("DUPLICATE_DECLARATION", exceptionHandler.getErrors().get(0).getMessage());
    }

    @Test
    public void testUndefinedReference() throws  IOException {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"question 1\" var1: string if (unknown) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);
        typeChecker.visit(form);
        assertEquals("UNDEFINED_REFERENCE", exceptionHandler.getErrors().get(0).getMessage());
    }
}
