using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class ArithmaticOperations
    {
        [TestMethod]
        public void DivisionValidOperators()
        {
            ValueTester.TestBinaryOperation("Divide", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, 5),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, 2.1m),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.0m, 1.0m),
                new BinaryOperationTestInput(10.0m, 20, 0.5m)
            }
            );
        }

        [TestMethod]
        public void DivisionInvalidOperators()
        {
            ValueTester.TestBinaryOperation("Divide", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimals
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34m, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(true, "string", null),
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(true, 12.345m, null),
                // String
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
            }
            );
        }

        [TestMethod]
        public void MultiplicationValidOperators()
        {
            ValueTester.TestBinaryOperation("Multiply", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, 20),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, 52.5m),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.0m, 100.0m),
                new BinaryOperationTestInput(10.0m, 20, 200m)
            }
            );
        }

        [TestMethod]
        public void MultiplicationInvalidOperators()
        {
            ValueTester.TestBinaryOperation("Multiply", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimals
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34m, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(true, "string", null),
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(true, 12.345m, null),
                // String
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
            }
            );
        }

        [TestMethod]
        public void AdditionValidOperators()
        {
            ValueTester.TestBinaryOperation("Add", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, 12),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, 15.5m),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.0m, 20.0m),
                new BinaryOperationTestInput(10.0m, 20, 30.0m)
            }
            );
        }

        [TestMethod]
        public void AdditionInvalidOperators()
        {
            ValueTester.TestBinaryOperation("Add", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimals
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34m, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(true, "string", null),
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(true, 12.345m, null),
                // String
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
            }
            );
        }

        [TestMethod]
        public void SubtractionValidOperators()
        {
            ValueTester.TestBinaryOperation("Subtract", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, 8),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, 5.5m),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.0m, 0.0m),
                new BinaryOperationTestInput(10.0m, 20, -10.0m)
            }
            );
        }

        [TestMethod]
        public void SubtractionInvalidOperators()
        {
            ValueTester.TestBinaryOperation("Add", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimals
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34m, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(true, "string", null),
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(true, 12.345m, null),
                // String
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
            }
            );
        }
    }
}
