package test.org.uva.taxfree.ql.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.Node;
import org.uva.taxfree.ql.model.node.blocks.BlockNode;
import org.uva.taxfree.ql.model.node.blocks.IfElseStatementNode;
import org.uva.taxfree.ql.model.node.blocks.IfStatementNode;
import org.uva.taxfree.ql.model.node.declarations.CalculationNode;
import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.node.expression.ParenthesizedExpressionNode;
import org.uva.taxfree.ql.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.ql.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.ql.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.ql.model.operators.AddOperator;
import org.uva.taxfree.ql.model.operators.GreaterThanOperator;
import org.uva.taxfree.ql.model.operators.SubtractOperator;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.DateType;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.StringType;

import java.util.ArrayList;
import java.util.List;

public class QuestionFormTest {
    private final SourceInfo mEmptySource = new SourceInfo(0, 0, 0, 0);
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
        QuestionForm form = new QuestionForm("SimpleForm", mSymbolTable);
        form.show();
    }


    @Test
    public void testSimpleQuestions() throws Exception {
        add(new DeclarationNode("What is your name?", "userName", new StringType(), mEmptySource));
        add(new DeclarationNode("Did you sell a house?", "hasSoldHouse", new BooleanType(), mEmptySource));
        add(new DeclarationNode("Did you buy a house?", "hasBoughtHouse", new BooleanType(), mEmptySource));
    }

    @Test
    public void testCalculatedQuestion() throws Exception {

        DeclarationNode QuestionSold = new DeclarationNode("What is the value of the sold house?", "soldHouseValue", new IntegerType(), mEmptySource);
        DeclarationNode QuestionBought = new DeclarationNode("What is the value of the bought house?", "boughtHouseValue", new IntegerType(), mEmptySource);

        add(QuestionSold);
        add(QuestionBought);

        VariableLiteralNode variableSold = new VariableLiteralNode("soldHouseValue", mEmptySource);
        VariableLiteralNode variableBought = new VariableLiteralNode("boughtHouseValue", mEmptySource);

        BinaryExpressionNode expCalc = new BinaryExpressionNode(variableSold, new SubtractOperator(), variableBought, mEmptySource);
        CalculationNode intCalc = new CalculationNode("Money balance:", "moneyBalance", new IntegerType(), expCalc, mEmptySource);

        add(expCalc);
        add(intCalc);

        Assert.assertEquals(expCalc.evaluate(), "0", "Nodes should be able to calculate the result");
        mSymbolTable.updateValue("soldHouseValue", "-50000");
        mSymbolTable.updateValue("boughtHouseValue", "50000");
        Assert.assertEquals(intCalc.resolveValue(), "100000", "Calculation should use new values");
    }

    @Test
    // TODO
    public void testSimpleIfElseStatement() throws Exception {
        List<Node> ifQuestions = new ArrayList<>();
        ifQuestions.add(createNestedIfStatement());
        List<Node> elseQuestions = new ArrayList<>();
        elseQuestions.add(new DeclarationNode("Am I in the else?", "isInElse", new BooleanType(), mEmptySource));
        IfElseStatementNode ifElse = new IfElseStatementNode(new BooleanLiteralNode(true, mEmptySource), ifQuestions, elseQuestions, mEmptySource);
        add(ifElse);
    }

    @Test
    public void testSimpleIfStatements() throws Exception {
        add(createNestedIfStatement());
    }

    private BlockNode createNestedIfStatement() {
        DeclarationNode boolQuestion = new DeclarationNode("Do you want to see the if declarations?", "hasSoldHouse", new BooleanType(), mEmptySource);
        add(boolQuestion);
        VariableLiteralNode soldHouseLiteral = new VariableLiteralNode("hasSoldHouse", mEmptySource);
        List<Node> questions = new ArrayList<>();
        questions.add(soldHouseLiteral);
        questions.add(new DeclarationNode("Toggle me on and off by selling your house", "sellYourHouse", new StringType(), mEmptySource));
        IfStatementNode questionIfStatement = new IfStatementNode(soldHouseLiteral, questions, mEmptySource);
        add(questionIfStatement);
        add(new DeclarationNode("Am I inbetween two if's?", "isInBetween", new StringType(), mEmptySource));
        VariableLiteralNode condition = new VariableLiteralNode("hasSoldHouse", mEmptySource);

        questions.clear();
        questions.add(condition);
        questions.add(new DeclarationNode("Am I inside the If declarations?", "isInsideIfStatement", new BooleanType(), mEmptySource));
        IfStatementNode booleanIfStatementNode = new IfStatementNode(soldHouseLiteral, questions, mEmptySource);
        return booleanIfStatementNode;

    }

    @Test
    public void testBooleanIf() throws Exception {
        ExpressionNode condition = new BooleanLiteralNode(true, mEmptySource);
        List<Node> questions = new ArrayList<Node>() {{
            add(new DeclarationNode("Hello, do you have a name?", "hasName", new BooleanType(), mEmptySource));
        }};
        add(new IfStatementNode(condition, questions, mEmptySource));

        List<Node> secondQuestions = new ArrayList<Node>() {{
            add(new BooleanLiteralNode(false, mEmptySource));
            add(new DeclarationNode("If you see me, something's wrong", "noName", new BooleanType(), mEmptySource));
        }};
        add(new IfStatementNode(condition, questions, mEmptySource));

    }

    @Test
    public void testConstantCondition() throws Exception {
        List<Node> questions = new ArrayList<>();
        ExpressionNode parenthesized = new ParenthesizedExpressionNode(CalcOnePlusFive(), mEmptySource);
        ExpressionNode cond = new BinaryExpressionNode(new IntegerLiteralNode(0, mEmptySource), new GreaterThanOperator(), parenthesized, mEmptySource);
        questions.add(cond);
        questions.add(new DeclarationNode("Do you see me?", "amIVisible?", new BooleanType(), mEmptySource));
        IfStatementNode ifStatement = new IfStatementNode(cond, questions, mEmptySource);
        add(ifStatement);
    }

    @Test
    public void testCalculatedLiteralField() throws Exception {
        CalculationNode intField = new CalculationNode("I'm showing two:", "two", new IntegerType(), new IntegerLiteralNode(2, mEmptySource), mEmptySource);
        add(intField);
    }

    @Test
    public void testIntFieldCalculation() throws Exception {
        add(new CalculationNode("The result of 1 + 5:", "six", new IntegerType(), CalcOnePlusFive(), mEmptySource));
    }

    @Test
    public void testTextFields() throws Exception {
        add(new DeclarationNode("What is your name?", "participantName", new StringType(), mEmptySource));
        add(new DeclarationNode("How many cars did you buy?", "textAmount", new IntegerType(), mEmptySource));
        add(new DeclarationNode("What date did you buy your last car?", "lastBoughtCar", new DateType(), mEmptySource));
    }

    private void add(Node n) {
        n.fillSymbolTable(mSymbolTable);
    }

    private ExpressionNode CalcOnePlusFive() {
        ExpressionNode calc = new BinaryExpressionNode(new IntegerLiteralNode(1, mEmptySource),
                new AddOperator(),
                new IntegerLiteralNode(5, mEmptySource),
                mEmptySource);
        return calc;
    }
}