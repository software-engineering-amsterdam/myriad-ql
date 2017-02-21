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
        mRoot.addChild(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
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
            testBooleanIf();
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

        mRoot.addChild(new StringQuestion("What is your name?", "userName"));
        mRoot.addChild(new BooleanQuestion("Did you sell a house?", "hasSoldHouse"));
    }

    @Test
    public void testSimpleIfStatement() throws Exception {
        BooleanQuestion boolQuestion = new BooleanQuestion("Do you want to see the if statement?", "hasSoldHouse");
        mRoot.addChild(boolQuestion);
        IfStatementNode ifStatement = new IfStatementNode();
        ifStatement.addChild(new VariableLiteralNode("hasSoldHouse"));
        ifStatement.addChild(new StringQuestion("Toggle me on and off by selling your house", "sellYourHouse"));
        ifStatement = new IfStatementNode();
        ConditionNode condition = new VariableLiteralNode("hasSoldHouse");
        ifStatement.setCondition(condition);
        mRoot.addChild(ifStatement);
        ifStatement.addChild(new BooleanQuestion("Am I inside the If statement?", "isInsideIfStatement"));


    }

    @Test
    public void testBooleanIf() throws Exception {
        IfStatementNode ifStatementNode = new IfStatementNode();
        mRoot.addChild(ifStatementNode);
        ConditionNode condition = new BooleanLiteralNode("true");
        ifStatementNode.addChild(condition);
        ifStatementNode.addChild(new BooleanQuestion("Hello, do you have a name?", "hasName"));
        ifStatementNode = new IfStatementNode();
        ifStatementNode.addChild(new BooleanLiteralNode("false"));
        ifStatementNode.addChild(new BooleanQuestion("If you see me, something's wrong", "noName"));

    }
}