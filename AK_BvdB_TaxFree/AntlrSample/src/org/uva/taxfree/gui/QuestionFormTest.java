package org.uva.taxfree.gui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.model.*;

import java.util.Timer;

public class QuestionFormTest {
    private FormNode mRoot;
    private QuestionForm mForm;

    @BeforeMethod
    public void setUp() throws Exception {
        mRoot = new FormNode("TaxForm");
        mForm = new QuestionForm(mRoot);
    }


    public static void main(String args[]) {
        QuestionFormTest main = new QuestionFormTest();
        main.executeMain();
    }

    public void executeMain() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            testSimpleIfStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        showForm();
    }

    private void showForm() {
        createRenderer(mForm);
        mForm.show();
    }

    private final int START_DELAY_MS = 1000;
    private final int INTERVAL_MS = 1000;
    private void createRenderer(QuestionForm form) {
        FormRenderer renderer = new FormRenderer(form);
        Timer timer = new Timer();
        timer.schedule(renderer, START_DELAY_MS, INTERVAL_MS);

    }

    @Test
    public void testSimpleQuestions() throws Exception {

        mRoot.addChild(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
        mRoot.addChild(new StringQuestion("What is your name?", "userName"));
        mRoot.addChild(new BooleanQuestion("Did you sell a house?", "hasSoldHouse"));
    }

    @Test
    public void testSimpleIfStatement() throws Exception {
        mRoot.addChild(new BooleanQuestion("Do you want to see the if statement?", "hasSoldHouse"));
        IfStatementNode ifStatement = new IfStatementNode("hasSoldHouse");
        mRoot.addChild(ifStatement);
        ifStatement.addChild(new BooleanQuestion("Am I inside the If statement?", "isInsideIfStatement"));
    }
}