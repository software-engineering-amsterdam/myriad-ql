package org.uva.taxfree.GUI;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.model.BooleanQuestion;
import org.uva.taxfree.model.FormNode;
import org.uva.taxfree.model.Node;
import org.uva.taxfree.model.StringQuestion;

public class QuestionFormTest {
    private Node mRoot;
    @BeforeMethod
    public void setUp() throws Exception {
        mRoot = new FormNode("TaxForm");
    }

    public static void main(String[] args) {
        QuestionFormTest test = new QuestionFormTest();
        try {
            test.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.testSimpleQuestions();
    }

    public void testSimpleQuestions() {

        mRoot.addChild(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
        mRoot.addChild(new BooleanQuestion("Did you sell a house?", "hasSoldHouse"));
        mRoot.addChild(new StringQuestion("What is your name?", "userName"));
        QuestionForm a = new QuestionForm(mRoot);
        a.show();

    }
}