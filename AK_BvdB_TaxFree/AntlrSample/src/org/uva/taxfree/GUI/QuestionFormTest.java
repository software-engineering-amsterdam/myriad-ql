package org.uva.taxfree.GUI;

import org.testng.annotations.Test;
import org.uva.taxfree.model.*;

import static org.testng.Assert.*;

public class QuestionFormTest {
    @Test
    public void testSimpleQuestions(){

        Node root = new FormNode("TaxForm", null);
        Node Q1 = new BoolQuestion("Did you buy a house?", "hasBoughtHouse", root);
        Node Q2 = new BoolQuestion("Did you sell a house?", "hasSoldHouse", root);
        Node Q3 = new StringQuestion("What is your name?", "userName", root);
        QuestionForm a = new QuestionForm();
        a.generateForm(root);

    }

}