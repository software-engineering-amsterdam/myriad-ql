package org.uva.taxfree.gui;

import org.testng.annotations.BeforeMethod;
import org.uva.taxfree.model.BooleanQuestion;
import org.uva.taxfree.model.FormNode;
import org.uva.taxfree.model.FormRenderer;
import org.uva.taxfree.model.StringQuestion;

import java.util.Timer;
import java.util.TimerTask;

public class QuestionFormTest {
    private FormNode mRoot;

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

    private void createRenderer(QuestionForm form){
        FormRenderer r = new FormRenderer(form);
        Timer t = new Timer();
        t.schedule(r, 1000,1000);
    }

    public void testSimpleQuestions() {

        mRoot.addChild(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
        mRoot.addChild(new StringQuestion("What is your name?", "userName"));
        mRoot.addChild(new BooleanQuestion("Did you sell a house?", "hasSoldHouse"));
        QuestionForm a = new QuestionForm(mRoot);
        a.show();
        createRenderer(a);


    }
}