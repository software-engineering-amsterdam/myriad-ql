package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.FormRenderer;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.model.node.blocks.IfElseStatementNode;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.expression.*;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.model.node.statement.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;

public class QuestionFormTest {
    private FormNode mRoot;
    private QuestionForm mForm;
    private final Set<Node> mCachedNodes = new LinkedHashSet<>();
    private final SymbolTable mSymbolTable = new SymbolTable();


    public static void main(String args[]) {
        QuestionFormTest main = new QuestionFormTest();
        main.executeMain();
    }

    public void executeMain() {

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

        mCachedNodes.add(new StringQuestion("What is your name?", "userName"));
        mCachedNodes.add(new BooleanQuestion("Did you sell a house?", "hasSoldHouse"));
        mCachedNodes.add(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
    }

    @Test
    public void testCalculatedQuestion() throws Exception {

        IntegerQuestion QuestionSold = new IntegerQuestion("What is the value of the sold house?", "soldHouseValue");
        IntegerQuestion QuestionBought = new IntegerQuestion("What is the value of the bought house?", "boughtHouseValue");

        mCachedNodes.add(QuestionSold);
        mCachedNodes.add(QuestionBought);

        VariableLiteralNode variableSold = new VariableLiteralNode("soldHouseValue", mSymbolTable);
        VariableLiteralNode variableBought = new VariableLiteralNode("boughtHouseValue", mSymbolTable);

        ExpressionNode expCalc = new CalculationExpressionNode(variableSold, "-", variableBought);
        IntegerCalculatedField intCalc = new IntegerCalculatedField("Money balance:", "moneyBalance", expCalc);

        Assert.assertEquals(expCalc.resolveValue(), "(0-0)", "Nodes should have ability to resolveValue data");
        Assert.assertEquals(expCalc.evaluate(), "0", "Nodes should be able to calculate the result");
        mCachedNodes.add(intCalc);
        expCalc.evaluate();
        mRoot = new FormNode("SampleForm", mCachedNodes);
    }

    @Test
    public void testSimpleIfElseStatement() throws Exception {
        Set<Node> questions = new LinkedHashSet<Node>();
        questions.add(new BooleanQuestion("Am I in the else?", "isInElse"));
        IfElseStatementNode ifElse = new IfElseStatementNode(createMultipleIfStatements(), questions);
        mCachedNodes.add(ifElse);
    }

    @Test
    public void testSimpleIfStatements() throws Exception {
        mCachedNodes.add(createMultipleIfStatements());
    }

    private IfStatementNode createMultipleIfStatements() {
        BooleanQuestion boolQuestion = new BooleanQuestion("Do you want to see the if statement?", "hasSoldHouse");
        mCachedNodes.add(boolQuestion);
        VariableLiteralNode soldHouseLiteral = new VariableLiteralNode("hasSoldHouse", mSymbolTable);
        Set<Node> questions = new LinkedHashSet<>();
        questions.add(soldHouseLiteral);
        questions.add(new StringQuestion("Toggle me on and off by selling your house", "sellYourHouse"));
        IfStatementNode questionIfStatement = new IfStatementNode(soldHouseLiteral, questions);
        mCachedNodes.add(questionIfStatement);
        mCachedNodes.add(new StringQuestion("Am I inbetween two if's?", "isInBetween"));
        VariableLiteralNode condition = new VariableLiteralNode("hasSoldHouse", mSymbolTable);

        questions.clear();
        questions.add(condition);
        questions.add(new BooleanQuestion("Am I inside the If statement?", "isInsideIfStatement"));
        IfStatementNode booleanIfStatementNode = new IfStatementNode(soldHouseLiteral, questions);
        return booleanIfStatementNode;

    }

    @Test
    public void testBooleanIf() throws Exception {
        ConditionNode condition = new BooleanLiteralNode("true");
        Set<Node> questions = new LinkedHashSet<Node>() {{
            add(new BooleanQuestion("Hello, do you have a name?", "hasName"));
        }};
        mCachedNodes.add(new IfStatementNode(condition, questions));

        Set<Node> secondQuestions = new LinkedHashSet<Node>() {{
            add(new BooleanLiteralNode("false"));
            add(new BooleanQuestion("If you see me, something's wrong", "noName"));
        }};
        mCachedNodes.add(new IfStatementNode(condition, questions));

    }

    @Test
    public void testConstantCondition() throws Exception {

        ConditionNode parenthesized = new ParenthesizedExpressionNode(CalcOnePlusFive());
        ConditionNode cond = new BooleanExpressionNode(new IntegerLiteralNode("0"), "<", parenthesized);
        Set<Node> questions = new LinkedHashSet<>();
        questions.add(cond);
        questions.add(new BooleanQuestion("Do you see me?", "amIVisible?"));
        IfStatementNode ifStatement = new IfStatementNode(cond, questions);
        mCachedNodes.add(ifStatement);
    }

    @Test
    public void testCalculatedLiteralField() throws Exception {
        CalculatedField intField = new IntegerCalculatedField("I'm showing two:", "two", new IntegerLiteralNode("2"));
        mCachedNodes.add(intField);
    }

    @Test
    public void testIntFieldCalculation() throws Exception {
        CalculatedField intField = new IntegerCalculatedField("The result of 1 + 5:", "six", CalcOnePlusFive());
        mCachedNodes.add(intField);
    }

    private ConditionNode CalcOnePlusFive() {
        ConditionNode calc = new CalculationExpressionNode(new IntegerLiteralNode("1"), "+", new IntegerLiteralNode("5"));
        return calc;
    }
}