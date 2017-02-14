package org.uva.taxfree.GUI;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.model.BooleanQuestion;
import org.uva.taxfree.model.FormNode;
import org.uva.taxfree.model.Node;
import org.uva.taxfree.model.StringQuestion;

public class QuestionFormTest {
    private Node root;
    @BeforeMethod
    public void setUp() throws Exception {
        root = new FormNode("TaxForm");
    }
    @Test
    public static void main(String[] args) {
        QuestionFormTest test = new QuestionFormTest();
        test.testSimpleQuestions();
    }

    public void testSimpleQuestions() {

        root.addChild(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
        root.addChild(new BooleanQuestion("Did you sell a house?", "hasSoldHouse"));
        root.addChild(new StringQuestion("What is your name?", "userName"));
        QuestionForm a = new QuestionForm(root);
        a.show();

    }

    public void testOperators(){
    }

}