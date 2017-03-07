package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.model.node.blocks.IfElseStatementNode;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.declarations.*;
import org.uva.taxfree.model.node.expression.*;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;

import java.util.LinkedHashSet;
import java.util.Set;

public class QuestionFormTest {
    private final Set<Node> mCachedNodes = new LinkedHashSet<>();
    private final Set<NamedNode> mCachedDeclarations = new LinkedHashSet<>();
    private final SymbolTable mSymbolTable = new SymbolTable();


    public static void main(String args[]) {
        QuestionFormTest main = new QuestionFormTest();
        main.executeMain();
    }

    public void executeMain() {

        try {
            testTextFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
        showForm();
    }

    private void showForm() {
        mSymbolTable.addDeclarations(mCachedDeclarations);
        QuestionForm form = new QuestionForm(new FormNode("SimpleForm", mCachedNodes));
        form.show();
    }


    @Test
    public void testSimpleQuestions() throws Exception {

        add(new StringQuestion("What is your name?", "userName"));
        add(new BooleanQuestion("Did you sell a house?", "hasSoldHouse"));
        add(new BooleanQuestion("Did you buy a house?", "hasBoughtHouse"));
    }

    @Test
    public void testCalculatedQuestion() throws Exception {

        IntegerQuestion QuestionSold = new IntegerQuestion("What is the value of the sold house?", "soldHouseValue");
        IntegerQuestion QuestionBought = new IntegerQuestion("What is the value of the bought house?", "boughtHouseValue");

        add(QuestionSold);
        add(QuestionBought);

        VariableLiteralNode variableSold = new VariableLiteralNode("soldHouseValue", mSymbolTable);
        VariableLiteralNode variableBought = new VariableLiteralNode("boughtHouseValue", mSymbolTable);

        BinaryExpressionNode expCalc = new CalculationBinaryExpressionNode(variableSold, "-", variableBought);
        IntegerCalculatedField intCalc = new IntegerCalculatedField("Money balance:", "moneyBalance", expCalc);

        Assert.assertEquals(expCalc.resolveValue(), "(0-0)", "Nodes should have ability to resolveValue data");
        Assert.assertEquals(expCalc.evaluate(), "0", "Nodes should be able to calculate the result");
        add(intCalc);
        expCalc.evaluate();
    }

    @Test
    public void testSimpleIfElseStatement() throws Exception {
        Set<Node> questions = new LinkedHashSet<>();
        questions.add(new BooleanQuestion("Am I in the else?", "isInElse"));
        IfElseStatementNode ifElse = new IfElseStatementNode(createMultipleIfStatements(), questions);
        add(ifElse);
    }

    @Test
    public void testSimpleIfStatements() throws Exception {
        add(createMultipleIfStatements());
    }

    private BlockNode createMultipleIfStatements() {
        BooleanQuestion boolQuestion = new BooleanQuestion("Do you want to see the if declarations?", "hasSoldHouse");
        add(boolQuestion);
        VariableLiteralNode soldHouseLiteral = new VariableLiteralNode("hasSoldHouse", mSymbolTable);
        Set<Node> questions = new LinkedHashSet<>();
        questions.add(soldHouseLiteral);
        questions.add(new StringQuestion("Toggle me on and off by selling your house", "sellYourHouse"));
        IfStatementNode questionIfStatement = new IfStatementNode(soldHouseLiteral, questions);
        add(questionIfStatement);
        add(new StringQuestion("Am I inbetween two if's?", "isInBetween"));
        VariableLiteralNode condition = new VariableLiteralNode("hasSoldHouse", mSymbolTable);

        questions.clear();
        questions.add(condition);
        questions.add(new BooleanQuestion("Am I inside the If declarations?", "isInsideIfStatement"));
        IfStatementNode booleanIfStatementNode = new IfStatementNode(soldHouseLiteral, questions);
        return booleanIfStatementNode;

    }

    @Test
    public void testBooleanIf() throws Exception {
        ExpressionNode condition = new BooleanLiteralNode("true");
        Set<Node> questions = new LinkedHashSet<Node>() {{
            add(new BooleanQuestion("Hello, do you have a name?", "hasName"));
        }};
        add(new IfStatementNode(condition, questions));

        Set<Node> secondQuestions = new LinkedHashSet<Node>() {{
            add(new BooleanLiteralNode("false"));
            add(new BooleanQuestion("If you see me, something's wrong", "noName"));
        }};
        add(new IfStatementNode(condition, questions));

    }

    @Test
    public void testConstantCondition() throws Exception {

        ExpressionNode parenthesized = new ParenthesizedExpressionNode(CalcOnePlusFive());
        ExpressionNode cond = new BooleanBinaryExpressionNode(new IntegerLiteralNode("0"), "<", parenthesized);
        Set<Node> questions = new LinkedHashSet<>();
        questions.add(cond);
        questions.add(new BooleanQuestion("Do you see me?", "amIVisible?"));
        IfStatementNode ifStatement = new IfStatementNode(cond, questions);
        add(ifStatement);
    }

    @Test
    public void testCalculatedLiteralField() throws Exception {
        CalculatedField intField = new IntegerCalculatedField("I'm showing two:", "two", new IntegerLiteralNode("2"));
        add(intField);
    }

    @Test
    public void testIntFieldCalculation() throws Exception {
        add(new IntegerCalculatedField("The result of 1 + 5:", "six", CalcOnePlusFive()));
    }

    @Test
    public void testTextFields() throws Exception {
        add(new StringQuestion("What is your name?", "participantName"));
        add(new IntegerQuestion("How many cars did you buy?", "textAmount"));
        add(new MoneyQuestion("How much money do you want to receive?", "moneyAmount"));
        add(new DateQuestion("What date did you buy your last car?", "lastBoughtCar"));
    }



    private void add(BlockNode blockNode) {
        Set<NamedNode> declarations = new LinkedHashSet<>();
        blockNode.retrieveDeclarations(declarations);
        mCachedDeclarations.addAll(declarations);
        addNode(blockNode);
    }

    private void add(NamedNode namedNode) {
        mCachedDeclarations.add(namedNode);
        addNode(namedNode);
    }

    private void addNode(Node n) {
        mCachedNodes.add(n);
    }

    private ExpressionNode CalcOnePlusFive() {
        ExpressionNode calc = new CalculationBinaryExpressionNode(new IntegerLiteralNode("1"), "+", new IntegerLiteralNode("5"));
        return calc;
    }
}