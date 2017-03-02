using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;


namespace Tests.QL.Value
{
    [TestClass]
    public class EqualityOperations
    {

        [TestMethod]        
        public void ValidOperators()
        {
            ValueTester.TestBinaryOperation("EqualTo", new[]
            {
                // Integers
                new BinaryOperationTestInput(1, 1, true),
                new BinaryOperationTestInput(1, 5, false),
                // Two decimals
                new BinaryOperationTestInput(12.34m, 12.34m, true),
                new BinaryOperationTestInput(12.34m, 12.35m, false),
                // Integer and decimal
                new BinaryOperationTestInput(10, 10.0m, true),
                new BinaryOperationTestInput(10, 20.0m, false),
                // Booleans
                new BinaryOperationTestInput(true, false, false),
                new BinaryOperationTestInput(true, true, true),
                new BinaryOperationTestInput(false, true, false),
                new BinaryOperationTestInput(false, false, true),
                // Strings
                new BinaryOperationTestInput("string", "string", true),
                new BinaryOperationTestInput("string", "String", false),
                new BinaryOperationTestInput("string", " string", false)               
            }
            );
        }

        [TestMethod]
        public void InalidOperators()
        {
            ValueTester.TestBinaryOperation("EqualTo", new[]
            {               
                // Integers,
                new BinaryOperationTestInput(1, true, null),
                new BinaryOperationTestInput(1, "string", null),
                // Decimals,
                new BinaryOperationTestInput(12.34m, true, null),
                new BinaryOperationTestInput(12.34m, "string", null),
                // Booleans,
                new BinaryOperationTestInput(true, 1, null),
                new BinaryOperationTestInput(true, 12.34m, null),
                new BinaryOperationTestInput(false, "string", null),
                // And Strings
                new BinaryOperationTestInput("string", 1, null),
                new BinaryOperationTestInput("string", 12.34m, null),
                new BinaryOperationTestInput("string", true, null)
            }

            );
        }
    }
}
