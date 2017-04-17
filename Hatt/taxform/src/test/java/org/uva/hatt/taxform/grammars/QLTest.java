package org.uva.hatt.taxform.grammars;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class QLTest {

    @Test
    public void emptyForm() throws IOException {
        String form = "form taxOfficeExample { }";
        String expected = "(form form taxOfficeExample { })";
        String tree = ASTGenerator.getParseStringTree(form);

        assertEquals(expected, tree);
    }

    @Test
    public void oneQuestion() throws IOException {
        String form = "form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: boolean }";
        String expected = "(form form taxOfficeExample { (items (question \"Did you sell a house in 2010?\" hasSoldHouse : (valueType boolean))) })";
        String tree = ASTGenerator.getParseStringTree(form);

        assertEquals(expected, tree);
    }

    @Test
    public void multipleQuestions() throws IOException {
        String form = "form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: boolean \"Did you buy a house in 2010?\" hasBoughtHouse: boolean }";
        String expected = "(form form taxOfficeExample { (items (question \"Did you sell a house in 2010?\" hasSoldHouse : (valueType boolean))) (items (question \"Did you buy a house in 2010?\" hasBoughtHouse : (valueType boolean))) })";
        String tree = ASTGenerator.getParseStringTree(form);

        assertEquals(expected, tree);
    }

    @Test
    public void singleIfBlock() throws IOException {
        String form = "form fA {if (hasSoldHouse) {\"qA?\" hasA: boolean}}";
        String expected = "(form form fA { (items (ifThen (ifBlock if ( (expression hasSoldHouse) ) { (items (question \"qA?\" hasA : (valueType boolean))) }))) })";
        String tree = ASTGenerator.getParseStringTree(form);

        assertEquals(expected, tree);
    }

    @Test
    public void singleIfElseBlock() throws IOException {
        String form = "form fA {if (hasSoldHouse) {\"qA?\" hasA: boolean} else { \"qB?\" hasB: boolean}}";
        String expected = "(form form fA { (items (ifThenElse (ifBlock if ( (expression hasSoldHouse) ) { (items (question \"qA?\" hasA : (valueType boolean))) }) (elseBlock else { (items (question \"qB?\" hasB : (valueType boolean))) }))) })";
        String tree = ASTGenerator.getParseStringTree(form);

        assertEquals(expected, tree);
    }

    @Test
    public void nestedIfBlock() throws IOException {
        String form = "form fA {if (hasSoldHouseA) {if (hasSoldHouseB) {\"qA?\" hasA: boolean}}}";
        String expected = "(form form fA { (items (ifThen (ifBlock if ( (expression hasSoldHouseA) ) { (items (ifThen (ifBlock if ( (expression hasSoldHouseB) ) { (items (question \"qA?\" hasA : (valueType boolean))) }))) }))) })";
        String tree = ASTGenerator.getParseStringTree(form);

        assertEquals(expected, tree);
    }

    @Test
    public void singleIfEqualityBlock() throws IOException {
        String form = "form fA { if (1 != 2) { \"qA?\" hasA: boolean } }";
        String expected = "(form form fA { (items (ifThen (ifBlock if ( (expression (expression 1) != (expression 2)) ) { (items (question \"qA?\" hasA : (valueType boolean))) }))) })";
        String tree = ASTGenerator.getParseStringTree(form);

        assertEquals(expected, tree);
    }

}
