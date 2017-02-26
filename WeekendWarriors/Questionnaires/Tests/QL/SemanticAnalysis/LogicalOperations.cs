using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.AST;

namespace Tests.QL.SemanticAnalysis
{
    [TestClass]
    public class LogicalOperations
    {
        protected SemanticTestHarness TestHarness = new SemanticTestHarness(ASTFactory.QLObjectType.Expression);

        [TestMethod]
        public void TestValidLogicalOperation()
        {
            TestHarness.TestExpression("true || false", 0, "Or operation on boolean literals");
            TestHarness.TestExpression("false && false", 0, "And operation on boolean lierals");
            TestHarness.TestExpression("(1 < 5) || false", 0, "Or operation on boolean expression result");
            TestHarness.TestExpression("(1 < 5) && false", 0, "Or operation on boolean expression result");
        }

        [TestMethod]
        public void TestInvalidLogicalOperation()
        {
            TestHarness.TestExpression("1 || false", 1, "Or operation on number and boolean");
            TestHarness.TestExpression("true || 1", 1, "Or operation on boolean and number");
            TestHarness.TestExpression("1 || 1", 1, "Or operation on number and number");

            TestHarness.TestExpression("1.23 || false", 1, "Or operation on decimal and boolean");
            TestHarness.TestExpression("true || 1.23", 1, "Or operation on boolean and decimal");
            TestHarness.TestExpression("1.23 || 1.23", 1, "Or operation on decimal and boolean");

            TestHarness.TestExpression("\"string\" || false", 1, "Or operation on string and boolean");
            TestHarness.TestExpression("true || \"string\"", 1, "Or operation on boolean and string");
            TestHarness.TestExpression("\"string\" || \"string\"", 1, "Or operation on string and string");


            TestHarness.TestExpression("1 && false", 1, "And operation on number and boolean");
            TestHarness.TestExpression("true && 1", 1, "And operation on boolean and number");
            TestHarness.TestExpression("1 && 1", 1, "And operation on number and number");

            TestHarness.TestExpression("1.23 && false", 1, "And operation on decimal and boolean");
            TestHarness.TestExpression("true && 1.23", 1, "And operation on boolean and decimal");
            TestHarness.TestExpression("1.23 && 1.23", 1, "And operation on decimal and boolean");

            TestHarness.TestExpression("\"string\" && false", 1, "And operation on string and boolean");
            TestHarness.TestExpression("true && \"string\"", 1, "And operation on boolean and string");
            TestHarness.TestExpression("\"string\" && \"string\"", 1, "And operation on string and string");
        }
    }
}
