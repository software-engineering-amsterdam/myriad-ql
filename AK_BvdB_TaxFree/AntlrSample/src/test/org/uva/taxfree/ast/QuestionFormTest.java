package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.gui.QuestionForm;
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
            testSimpleIfElseStatement();
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
        mRoot.addChild(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
    }

    @Test
    public void testCalculatedQuestion() throws Exception {

        IntegerQuestion QuestionSold = new IntegerQuestion("What is the value of the sold house?", "soldHouseValue");
        IntegerQuestion QuestionBought = new IntegerQuestion("What is the value of the bought house?", "boughtHouseValue");

        mRoot.addChild(QuestionSold);
        mRoot.addChild(QuestionBought);

        IntegerCalculatedField intCalc = new IntegerCalculatedField("Money balance:", "moneyBalance");
        ExpressionNode expCalc = new CalculationExpressionNode("-");
        intCalc.addChild(expCalc);
        VariableLiteralNode variableSold = new VariableLiteralNode("soldHouseValue");
        variableSold.setReference(QuestionSold);

        VariableLiteralNode variableBought = new VariableLiteralNode("boughtHouseValue");
        variableBought.setReference(QuestionBought);

        expCalc.addChild(variableSold);
        expCalc.addChild(variableBought);

        Assert.assertEquals(expCalc.resolve(), "(0-0)", "Nodes should have ability to resolve data");
        Assert.assertEquals(expCalc.evaluate(), "0", "Nodes should be able to calculate the result");
        mRoot.addChild(intCalc);
        expCalc.evaluate();
    }

    @Test
    public void testSimpleIfElseStatement() throws Exception {
        IfStatementNode ifStatementNode = createMultipleIfStatements();
        IfElseStatementNode ifElse = new IfElseStatementNode();
        ifElse.addChild(ifStatementNode);
        BooleanQuestion booleanQuestion = new BooleanQuestion("Am I in the else?", "isInElse");
        ifElse.addChild(booleanQuestion);
        mRoot.addChild(ifElse);
    }

    @Test
    public void testSimpleIfStatements() throws Exception {
        mRoot.addChild(createMultipleIfStatements());
    }

    private IfStatementNode createMultipleIfStatements() {
        BooleanQuestion boolQuestion = new BooleanQuestion("Do you want to see the if statement?", "hasSoldHouse");
        mRoot.addChild(boolQuestion);
        IfStatementNode questionIfStatement = new IfStatementNode();
        VariableLiteralNode soldHouseLiteral = new VariableLiteralNode("hasSoldHouse");
        soldHouseLiteral.setReference(boolQuestion);
        questionIfStatement.addChild(soldHouseLiteral);
        questionIfStatement.addChild(new StringQuestion("Toggle me on and off by selling your house", "sellYourHouse"));
        mRoot.addChild(questionIfStatement);

        mRoot.addChild(new StringQuestion("Am I inbetween two if's?", "isInBetween"));

        IfStatementNode booleanIfStatementNode = new IfStatementNode();
        VariableLiteralNode condition = new VariableLiteralNode("hasSoldHouse");
        condition.setReference(boolQuestion);
        booleanIfStatementNode.addChild(condition);
        booleanIfStatementNode.addChild(new BooleanQuestion("Am I inside the If statement?", "isInsideIfStatement"));
        return booleanIfStatementNode;

    }

    @Test
    public void testBooleanIf() throws Exception {
        IfStatementNode ifStatementNode = new IfStatementNode();
        ConditionNode condition = new BooleanLiteralNode("true");
        ifStatementNode.addChild(condition);
        ifStatementNode.addChild(new BooleanQuestion("Hello, do you have a name?", "hasName"));
        ifStatementNode = new IfStatementNode();
        ifStatementNode.addChild(new BooleanLiteralNode("false"));
        ifStatementNode.addChild(new BooleanQuestion("If you see me, something's wrong", "noName"));

    }

    @Test
    public void testConstantCondition() throws Exception {
        IfStatementNode ifStatement = new IfStatementNode();
        ConditionNode cond = new BooleanExpressionNode("<");
        ifStatement.addChild(cond);
        cond.addChild(new IntegerLiteralNode("0"));
        ConditionNode parenthesized = new ParenthesizedExpressionNode();
        cond.addChild(parenthesized);
        parenthesized.addChild(CalcOnePlusFive());
        ifStatement.addChild(new BooleanQuestion("Do you see me?", "amIVisible?"));
        mRoot.addChild(ifStatement);
    }

    @Test
    public void testCalculatedLiteralField() throws Exception {
        CalculatedField intField = new IntegerCalculatedField("I'm showing two:", "two");
        intField.addChild(new IntegerLiteralNode("2"));
        mRoot.addChild(intField);
    }

    @Test
    public void testIntFieldCalculation() throws Exception {
        CalculatedField intField = new IntegerCalculatedField("The result of 1 + 5:", "six");
        intField.addChild(CalcOnePlusFive());
        mRoot.addChild(intField);
    }

    private Node CalcOnePlusFive() {
        ConditionNode calc = new CalculationExpressionNode("+");
        calc.addChild(new IntegerLiteralNode("1"));
        calc.addChild(new IntegerLiteralNode("5"));
        return calc;
    }
}