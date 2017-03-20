package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.model.node.declarations.CalculationNode;
import org.uva.taxfree.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.model.node.operators.AddOperator;
import org.uva.taxfree.model.node.operators.NumericOperator;
import org.uva.taxfree.model.types.IntegerType;

import java.util.Set;

public class QuestionChecks {

    @Test
    public void checkCalculationDependencies() throws Exception {
        CalculationNode calc = new CalculationNode("This is myCalc:", "myCalc",
                new IntegerType(),
                new BinaryExpressionNode(new VariableLiteralNode("myCalcA"),
                        new AddOperator(),
                        new VariableLiteralNode("myCalcB")));

        Set<String> usedVariables = calc.getUsedVariables();
        Assert.assertEquals(usedVariables.size(), 2);
    }
}
