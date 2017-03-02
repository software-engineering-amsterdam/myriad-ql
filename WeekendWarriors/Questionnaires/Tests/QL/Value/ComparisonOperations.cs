using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class ComparisonOperations
    {
        [TestMethod]
        public void GreaterThanValidOperators()
        {
            ValueTester.TestBinaryOperation("GreaterThan", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, true),
                new BinaryOperationTestInput(10, 11, false),
                new BinaryOperationTestInput(10, 10, false),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, true),
                new BinaryOperationTestInput(12.34m, 23.45m, false),
                new BinaryOperationTestInput(12.34m, 12.34m, false),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.1m, false),
                new BinaryOperationTestInput(10.1m, 10, true),
                new BinaryOperationTestInput(10.0m, 10, false),
                new BinaryOperationTestInput(10, 10.0m, false)
            }
            );
        }

        [TestMethod]
        public void GreaterThanInValidOperators()
        {
            ValueTester.TestBinaryOperation("GreaterThan", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimal
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(false, 12.345m, null),
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(false, "string", null),
                // String
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
            }
            );
        }

        [TestMethod]
        public void GreaterThanOrEqualValidOperators()
        {
            ValueTester.TestBinaryOperation("GreaterThanOrEqual", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, true),
                new BinaryOperationTestInput(10, 11, false),
                new BinaryOperationTestInput(10, 10, true),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, true),
                new BinaryOperationTestInput(12.34m, 23.45m, false),
                new BinaryOperationTestInput(12.34m, 12.34m, true),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.1m, false),
                new BinaryOperationTestInput(10.1m, 10, true),
                new BinaryOperationTestInput(10.0m, 10, true),
                new BinaryOperationTestInput(10, 10.0m, true)
            }
            );
        }

        [TestMethod]
        public void GreaterThanOrEqualInValidOperators()
        {
            ValueTester.TestBinaryOperation("GreaterThanOrEqual", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimal
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(false, 12.345m, null),
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(false, "string", null),
                // String
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
            }
            );
        }

        [TestMethod]
        public void LessThanValidOperators()
        {
            ValueTester.TestBinaryOperation("LessThan", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, false),
                new BinaryOperationTestInput(10, 11, true),
                new BinaryOperationTestInput(10, 10, false),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, false),
                new BinaryOperationTestInput(12.34m, 23.45m, true),
                new BinaryOperationTestInput(12.34m, 12.34m, false),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.1m, true),
                new BinaryOperationTestInput(10.1m, 10, false),
                new BinaryOperationTestInput(10.0m, 10, false),
                new BinaryOperationTestInput(10, 10.0m, false)
            }
            );
        }

        [TestMethod]
        public void LessThanInvalidOperators()
        {
            ValueTester.TestBinaryOperation("GreaterThanOrEqual", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimal
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(false, 12.345m, null),
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(false, "string", null),
                // String
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
            }
            );
        }

        [TestMethod]
        public void LessthanOrEqualValidOperators()
        {
            ValueTester.TestBinaryOperation("LessThanOrEqual", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, 2, false),
                new BinaryOperationTestInput(10, 11, true),
                new BinaryOperationTestInput(10, 10, true),
                // Decimals
                new BinaryOperationTestInput(10.5m, 5.0m, false),
                new BinaryOperationTestInput(12.34m, 23.45m, true),
                new BinaryOperationTestInput(12.34m, 12.34m, true),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.1m, true),
                new BinaryOperationTestInput(10.1m, 10, false),
                new BinaryOperationTestInput(10.0m, 10, true),
                new BinaryOperationTestInput(10, 10.0m, true)
            }
            );
        }

        [TestMethod]
        public void LessThanOrEqualInvalidOperators()
        {
            ValueTester.TestBinaryOperation("GreaterThanOrEqual", new[]
            {
                // Integers
                new BinaryOperationTestInput(10, true, null),
                new BinaryOperationTestInput(10, "string", null),
                // Decimal
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34, "string", null),
                // Boolean
                new BinaryOperationTestInput(true, 12345, null),
                new BinaryOperationTestInput(false, 12.345m, null),
                new BinaryOperationTestInput(true, true, null),
                new BinaryOperationTestInput(false, "string", null),
                // String
                new BinaryOperationTestInput("string", 12345, null),
                new BinaryOperationTestInput("string", 12.345m, null),
                new BinaryOperationTestInput("string", true, null),
                new BinaryOperationTestInput("string", "string", null),
            }
            );
        }
    }
}
