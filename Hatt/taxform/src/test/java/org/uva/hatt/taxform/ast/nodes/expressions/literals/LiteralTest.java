package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.junit.Test;
import org.uva.hatt.taxform.parsing.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.nodes.items.IfThen;
import org.uva.hatt.taxform.ast.nodes.items.Item;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class LiteralTest {

    @Test
    public void testBooleanLiteral() throws IOException {
        String qlForm = "form taxOfficeExample { if (true) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(BooleanLiteral.class));
    }

    @Test
    public void testIdentifier() throws IOException {
        String qlForm = "form taxOfficeExample { if (hasSoldHouse) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(Identifier.class));
    }

    @Test
    public void testIntegerLiteral() throws IOException {
        String qlForm = "form taxOfficeExample { if (1) { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(IntegerLiteral.class));
    }

    @Test
    public void testStringLiteral() throws IOException {
        String qlForm = "form taxOfficeExample { if (\"str\") { } }";
        Form form = ASTGenerator.getForm(qlForm);

        List<Item> questions = form.getQuestions();
        IfThen ifThen = (IfThen) questions.get(0);

        assertThat(ifThen.getCondition(), instanceOf(StringerLiteral.class));
    }

}