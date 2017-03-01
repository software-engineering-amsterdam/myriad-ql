using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.Value;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.QL.Value
{
    class ValueTester
    {
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
