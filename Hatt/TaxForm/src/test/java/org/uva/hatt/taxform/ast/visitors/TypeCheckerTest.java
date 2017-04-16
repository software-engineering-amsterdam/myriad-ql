package org.uva.hatt.taxform.ast.visitors;

import org.junit.Test;
import org.uva.hatt.taxform.ast.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.items.Item;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.typeChecker.TypeChecker;
import org.uva.hatt.taxform.typeChecker.messages.Message;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class TypeCheckerTest {
    @Test
    public void testDuplicateLabel() throws IOException {
        Message exceptionHandler = new Message();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"Same label question\" hasSoldHouse: string \"Same label question\" sellingPrice: money }";
        Form form = ASTGenerator.getForm(qlForm);
        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);
        typeChecker.visit(q1);
        typeChecker.visit(q2);
        assertEquals("Duplicate question label at line 1: Label: Same label question", exceptionHandler.getWarnings().get(0).getMessage());
    }

    @Test
    public void testDuplicteDeclaration() throws IOException {
        Message exceptionHandler = new Message();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"question 1\" var: string \"question 2\" var: money }";
        Form form = ASTGenerator.getForm(qlForm);
        List<Item> questions = form.getQuestions();
        Question q1 = (Question) questions.get(0);
        Question q2 = (Question) questions.get(1);
        typeChecker.visit(q1);
        typeChecker.visit(q2);
        assertEquals("Duplicate question declaration at line 1: declaration: var", exceptionHandler.getErrors().get(0).getMessage());
    }

    @Test
    public void testUndefinedReference() throws  IOException {
        Message exceptionHandler = new Message();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"question 1\" var1: string if (unknown) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);
        typeChecker.visit(form);
        assertEquals("Undefined question reference at line 1: reference: unknown", exceptionHandler.getErrors().get(0).getMessage());
    }

    @Test
    public void testTypeMismatch() throws  IOException {
        Message exceptionHandler = new Message();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"question 1\" var1: string if (var1) {\"question 2\" var2: money } }";
        Form form = ASTGenerator.getForm(qlForm);
        typeChecker.visit(form);
        assertEquals("Type mismatch at line 1: expected boolean but got: String", exceptionHandler.getErrors().get(0).getMessage());
    }

    @Test
    public void testInvalidOperandsTypeToOperator() throws  IOException {
        Message exceptionHandler = new Message();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form f1 { \"question 1\" var1: boolean = 1 + var1 }";
        Form form = ASTGenerator.getForm(qlForm);
        typeChecker.visit(form);
        assertEquals("Invalid operand type at line 1, to operation: Addition", exceptionHandler.getErrors().get(0).getMessage());
    }

    @Test
    public void testCyclicDependency() throws  IOException {
        Message exceptionHandler = new Message();
        TypeChecker typeChecker = new TypeChecker(exceptionHandler);
        String qlForm = "form taxOfficeExample { \"q1?\" val1: money = 5 - 1  \n \"q2?\" val2: money = 5 + 1  \n \"q3?\" val3: money = val1 + val2}";;
        Form form = ASTGenerator.getForm(qlForm);
        typeChecker.visit(form);
        assertEquals("Cyclic dependency at line 3: Identifier 'val3' is dependent on 'val2'", exceptionHandler.getErrors().get(0).getMessage());
        assertEquals("Cyclic dependency at line 3: Identifier 'val3' is dependent on 'val1'", exceptionHandler.getErrors().get(1).getMessage());
    }
}
