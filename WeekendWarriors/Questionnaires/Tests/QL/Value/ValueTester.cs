using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Reflection;

namespace Tests.QL.Value
{
    class ValueTester
    {
        public static void TestBinaryOperation(string operation, BinaryOperationTestInput[] testSet)
        {            
            foreach (var testCase in testSet)
            {
                try
                {
                    TestBinaryOperation(testCase, operation);
                    Assert.IsTrue(testCase.ExpectedResult != null, string.Format("Operation {0} on operand types {1} and {2} is erroneously supported", operation, testCase.LeftHandSideOperand.GetType(), testCase.RightHandSideOperand.GetType()));
                }
                catch (NotSupportedException)
                {
                    Assert.IsTrue(testCase.ExpectedResult == null, string.Format("Operation {0} on operand types {1} and {2} is not supported while it should be", operation, testCase.LeftHandSideOperand.GetType(), testCase.RightHandSideOperand.GetType()));
                }
            }            
        }

        // Use reflection to test operators
        public static void TestBinaryOperation(BinaryOperationTestInput testInput, string op)
        {
            var lhsValue = ValueCreator.CreateValue(testInput.LeftHandSideOperand);
            var rhsValue = ValueCreator.CreateValue(testInput.RightHandSideOperand);
            var operationMethod = lhsValue.GetType().GetMethod(op, new Type[] { rhsValue.GetType() });
            try
            {
                var result = operationMethod.Invoke(lhsValue, new object[] { (dynamic)rhsValue });
                ValueTester.Test((dynamic)result, testInput.ExpectedResult);
            }
            catch (TargetInvocationException ex)
            {
                throw ex.InnerException;
            }
        }

        public static void Test(IValue value, object expected)
        {
            // We will only get here if none of the methods below match
            // That means that the types of the value and the expected value 
            // don't match.
            Assert.Fail();
        }

        public static void Test(BoolValue value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(bool));
            Assert.AreEqual((bool)expected, value.GetValue());
        }

        public static void Test(IntValue value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(int));
            Assert.AreEqual((int)expected, value.GetValue());
        }

        public static void Test(DecimalValue value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(decimal));
            Assert.AreEqual((decimal)expected, value.GetValue());
        }

        public static void Test(StringValue value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(string));
            Assert.AreEqual((string)expected, value.GetValue());
        }
    }
}
