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
import org.uva.taxfree.model.node.declarations.CalculationNode;
import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.node.expression.*;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.DateType;
import org.uva.taxfree.model.types.IntegerType;
import org.uva.taxfree.model.types.StringType;

import java.util.LinkedHashSet;
import java.util.Set;

public class QuestionFormTest {
    private final Set<Node> mCachedNodes = new LinkedHashSet<>();
    private final Set<DeclarationNode> mCachedDeclarations = new LinkedHashSet<>();
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
        mSymbolTable.addDepencendies(mCachedDeclarations);
        QuestionForm form = new QuestionForm(new FormNode("SimpleForm", mCachedNodes));
        form.show();
    }


    @Test
    public void testSimpleQuestions() throws Exception {

        add(new DeclarationNode("What is your name?", "userName", new StringType()));
        add(new DeclarationNode("Did you sell a house?", "hasSoldHouse", new BooleanType()));
        add(new DeclarationNode("Did you buy a house?", "hasBoughtHouse", new BooleanType()));
    }

    @Test
    public void testCalculatedQuestion() throws Exception {

        DeclarationNode QuestionSold = new DeclarationNode("What is the value of the sold house?", "soldHouseValue", new IntegerType());
        DeclarationNode QuestionBought = new DeclarationNode("What is the value of the bought house?", "boughtHouseValue", new IntegerType());

        add(QuestionSold);
        add(QuestionBought);

        VariableLiteralNode variableSold = new VariableLiteralNode("soldHouseValue", mSymbolTable);
        VariableLiteralNode variableBought = new VariableLiteralNode("boughtHouseValue", mSymbolTable);

        BinaryExpressionNode expCalc = new CalculationBinaryExpressionNode(variableSold, "-", variableBought);
        CalculationNode intCalc = new CalculationNode("Money balance:", "moneyBalance", new IntegerType(), expCalc);

        Assert.assertEquals(expCalc.resolveValue(), "(0-0)", "Nodes should have ability to resolveValue data");
        Assert.assertEquals(expCalc.evaluate(), "0", "Nodes should be able to calculate the result");
        add(intCalc);
        expCalc.evaluate();
    }

    @Test
    public void testSimpleIfElseStatement() throws Exception {
        Set<Node> questions = new LinkedHashSet<>();
        questions.add(new DeclarationNode("Am I in the else?", "isInElse", new BooleanType()));
        IfElseStatementNode ifElse = new IfElseStatementNode(createMultipleIfStatements(), questions);
        add(ifElse);
    }

    @Test
    public void testSimpleIfStatements() throws Exception {
        add(createMultipleIfStatements());
    }

    private BlockNode createMultipleIfStatements() {
        DeclarationNode boolQuestion = new DeclarationNode("Do you want to see the if declarations?", "hasSoldHouse", new BooleanType());
        add(boolQuestion);
        VariableLiteralNode soldHouseLiteral = new VariableLiteralNode("hasSoldHouse", mSymbolTable);
        Set<Node> questions = new LinkedHashSet<>();
        questions.add(soldHouseLiteral);
        questions.add(new DeclarationNode("Toggle me on and off by selling your house", "sellYourHouse", new StringType()));
        IfStatementNode questionIfStatement = new IfStatementNode(soldHouseLiteral, questions);
        add(questionIfStatement);
        add(new DeclarationNode("Am I inbetween two if's?", "isInBetween", new StringType()));
        VariableLiteralNode condition = new VariableLiteralNode("hasSoldHouse", mSymbolTable);

        questions.clear();
        questions.add(condition);
        questions.add(new DeclarationNode("Am I inside the If declarations?", "isInsideIfStatement", new BooleanType()));
        IfStatementNode booleanIfStatementNode = new IfStatementNode(soldHouseLiteral, questions);
        return booleanIfStatementNode;

    }

    @Test
    public void testBooleanIf() throws Exception {
        ExpressionNode condition = new BooleanLiteralNode("true");
        Set<Node> questions = new LinkedHashSet<Node>() {{
            add(new DeclarationNode("Hello, do you have a name?", "hasName", new BooleanType()));
        }};
        add(new IfStatementNode(condition, questions));

        Set<Node> secondQuestions = new LinkedHashSet<Node>() {{
            add(new BooleanLiteralNode("false"));
            add(new DeclarationNode("If you see me, something's wrong", "noName", new BooleanType()));
        }};
        add(new IfStatementNode(condition, questions));

    }

    @Test
    public void testConstantCondition() throws Exception {

        ExpressionNode parenthesized = new ParenthesizedExpressionNode(CalcOnePlusFive());
//        ExpressionNode cond = new BooleanBinaryExpressionNode(new IntegerLiteralNode("0"), new LessThanOperator(), parenthesized);
        Set<Node> questions = new LinkedHashSet<>();
//        questions.add(cond);
        questions.add(new DeclarationNode("Do you see me?", "amIVisible?", new BooleanType()));
//        IfStatementNode ifStatement = new IfStatementNode(cond, questions);
//        add(ifStatement);
    }

    @Test
    public void testCalculatedLiteralField() throws Exception {
        CalculationNode intField = new CalculationNode("I'm showing two:", "two", new IntegerType(), new IntegerLiteralNode("2"));
        add(intField);
    }

    @Test
    public void testIntFieldCalculation() throws Exception {
        add(new CalculationNode("The result of 1 + 5:", "six", new IntegerType(), CalcOnePlusFive()));
    }

    @Test
    public void testTextFields() throws Exception {
        add(new DeclarationNode("What is your name?", "participantName", new StringType()));
        add(new DeclarationNode("How many cars did you buy?", "textAmount", new IntegerType()));
        add(new DeclarationNode("What date did you buy your last car?", "lastBoughtCar", new DateType()));
    }


    private void add(BlockNode blockNode) {
        Set<DeclarationNode> declarations = new LinkedHashSet<>();
//        blockNode.retrieveDeclarations(declarations);
        mCachedDeclarations.addAll(declarations);
        addNode(blockNode);
    }

    private void add(DeclarationNode declarationNode) {
        mCachedDeclarations.add(declarationNode);
        addNode(declarationNode);
    }

    private void addNode(Node n) {
        mCachedNodes.add(n);
    }

    private ExpressionNode CalcOnePlusFive() {
        ExpressionNode calc = new CalculationBinaryExpressionNode(new IntegerLiteralNode("1"), "+", new IntegerLiteralNode("5"));
        return calc;
    }
}