using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class LogicalOperations
    {
        [TestMethod]
        public void AndValidOperators()
        {
            ValueTester.TestBinaryOperation("And", new[]
            {
                new BinaryOperationTestInput(false, false, false),
                new BinaryOperationTestInput(false, true, false),
                new BinaryOperationTestInput(true, false, false),
                new BinaryOperationTestInput(true, true, true)
            }
            );
        }

        [TestMethod]
        public void AndInvalidOperators()
        {
            ValueTester.TestBinaryOperation("And", new[]
            {
                // Integer
                new BinaryOperationTestInput(12345, 12345, null),
                new BinaryOperationTestInput(12345, 12.345m, null),
                new BinaryOperationTestInput(12345, "string", null),
                new BinaryOperationTestInput(12345, true, null),
                // Decimal
                new BinaryOperationTestInput(12.345m, 12345, null),
                new BinaryOperationTestInput(12.345m, 12.345m, null),
                new BinaryOperationTestInput(12.345m, "string", null),
                new BinaryOperationTestInput(12.345m, true, null),
                // String
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
                new BinaryOperationTestInput("string", "string", null),
                new BinaryOperationTestInput("string", true, null),
                // Bool
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(false, 12.345m, null),
                new BinaryOperationTestInput(true, "string", null)
            }
            );
        }

        [TestMethod]
        public void OrValidOperators()
        {
            ValueTester.TestBinaryOperation("Or", new[]
            {
                new BinaryOperationTestInput(false, false, false),
                new BinaryOperationTestInput(false, true, true),
                new BinaryOperationTestInput(true, false, true),
                new BinaryOperationTestInput(true, true, true)
            }
            );
        }

        [TestMethod]
        public void OrInvalidOperators()
        {
            ValueTester.TestBinaryOperation("Or", new[]
            {
                // Integer
                new BinaryOperationTestInput(12345, 12345, null),
                new BinaryOperationTestInput(12345, 12.345m, null),
                new BinaryOperationTestInput(12345, "string", null),
                new BinaryOperationTestInput(12345, true, null),
                // Decimal
                new BinaryOperationTestInput(12.345m, 12345, null),
                new BinaryOperationTestInput(12.345m, 12.345m, null),
                new BinaryOperationTestInput(12.345m, "string", null),
                new BinaryOperationTestInput(12.345m, true, null),
                // String
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
                new BinaryOperationTestInput("string", "string", null),
                new BinaryOperationTestInput("string", true, null),
                // Bool
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(false, 12.345m, null),
                new BinaryOperationTestInput(true, "string", null)
            }
            );
        }

    }
}
