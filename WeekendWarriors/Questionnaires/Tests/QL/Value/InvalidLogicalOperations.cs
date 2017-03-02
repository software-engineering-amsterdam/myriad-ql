using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class InvalidLogicalOperations
    {
        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on int and int was inappropriately allowed")]
        public void IntAndIntOperation()
        {
            var result = ValueCreator.CreateValue(12345).And(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on int and decimal was inappropriately allowed")]
        public void IntAndDecimalOperation()
        {
            var result = ValueCreator.CreateValue(12345).And(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on int and string was inappropriately allowed")]
        public void IntAndStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).And(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on int and bool was inappropriately allowed")]
        public void IntAndBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345).And(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on decimal and int was inappropriately allowed")]
        public void DecimalAndIntOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).And(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on decimal and decimal was inappropriately allowed")]
        public void DecimalAndDecimalOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).And(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on decimal and string was inappropriately allowed")]
        public void DecimalAndStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).And(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on decimal and bool was inappropriately allowed")]
        public void DecimalAndBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).And(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on string and int was inappropriately allowed")]
        public void StringAndIntOperation()
        {
            var result = ValueCreator.CreateValue("string").And(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on string and decimal was inappropriately allowed")]
        public void StringAndDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").And(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on string and string was inappropriately allowed")]
        public void StringAndStringOperation()
        {
            var result = ValueCreator.CreateValue("string").And(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on string and bool was inappropriately allowed")]
        public void StringAndBoolOperation()
        {
            var result = ValueCreator.CreateValue("string").And(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on boolean and int was inappropriately allowed")]
        public void BooleanAndIntOperation()
        {
            var result = ValueCreator.CreateValue(true).And(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on boolean and decimal was inappropriately allowed")]
        public void BooleanAndDecimalOperation()
        {
            var result = ValueCreator.CreateValue(false).And(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "And operation on boolean and string was inappropriately allowed")]
        public void BooleanAndStringOperation()
        {
            var result = ValueCreator.CreateValue(true).And(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on int and int was inappropriately allowed")]
        public void IntOrIntOperation()
        {
            var result = ValueCreator.CreateValue(12345).Or(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on int and decimal was inappropriately allowed")]
        public void IntOrDecimalOperation()
        {
            var result = ValueCreator.CreateValue(12345).Or(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on int and string was inappropriately allowed")]
        public void IntOrStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).Or(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on int and bool was inappropriately allowed")]
        public void IntOrBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345).Or(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on decimal and int was inappropriately allowed")]
        public void DecimalOrIntOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Or(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on decimal and decimal was inappropriately allowed")]
        public void DecimalOrDecimalOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Or(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on decimal and string was inappropriately allowed")]
        public void DecimalOrStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Or(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on decimal and bool was inappropriately allowed")]
        public void DecimalOrBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Or(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on string and int was inappropriately allowed")]
        public void StringOrIntOperation()
        {
            var result = ValueCreator.CreateValue("string").Or(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on string and decimal was inappropriately allowed")]
        public void StringOrDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").Or(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on string and string was inappropriately allowed")]
        public void StringOrStringOperation()
        {
            var result = ValueCreator.CreateValue("string").Or(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on string and bool was inappropriately allowed")]
        public void StringOrBoolOperation()
        {
            var result = ValueCreator.CreateValue("string").Or(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on boolean and int was inappropriately allowed")]
        public void BooleanOrIntOperation()
        {
            var result = ValueCreator.CreateValue(true).Or(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on boolean and decimal was inappropriately allowed")]
        public void BooleanOrDecimalOperation()
        {
            var result = ValueCreator.CreateValue(false).Or(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Or operation on boolean and string was inappropriately allowed")]
        public void BooleanOrStringOperation()
        {
            var result = ValueCreator.CreateValue(true).Or(ValueCreator.CreateValue("string"));
        }
    }
}
