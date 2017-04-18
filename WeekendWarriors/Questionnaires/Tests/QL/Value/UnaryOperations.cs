using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class UnaryOperations
    {
        [TestMethod]
        public void BangOperation()
        {
            ValueTester.TestBinaryOperation("Bang", new[]
            {
                // Valid (boolean)
                new OperationTestInput(false, true),
                new OperationTestInput(true, false),
                // Invalid:
                new OperationTestInput(12345, null),
                new OperationTestInput(12.345m, null),
                new OperationTestInput("string", null),
            }
            );
        }

        [TestMethod]
        public void PositiveOperation()
        {
            ValueTester.TestBinaryOperation("Positive", new[]
            {
                // Valid (integers and decimals)
                new OperationTestInput(12345, 12345),
                new OperationTestInput(12.345m, 12.345m),
                // Invalid:
                new OperationTestInput(true, null),
                new OperationTestInput("string", null),
            }
            );
        }

        [TestMethod]
        public void NegativeOperation()
        {
            ValueTester.TestBinaryOperation("Negative", new[]
            {
                // Valid (integers and decimals)
                new OperationTestInput(12345, -12345),
                new OperationTestInput(12.345m, -12.345m),
                // Invalid:
                new OperationTestInput(true, null),
                new OperationTestInput("string", null),
            }
            );
        }

    }
}
