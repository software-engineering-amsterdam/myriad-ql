using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class InvalidComparison
    {
        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on bool and int was inappropriately allowed")]
        public void BoolGTIntOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThan(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on bool and decimal was inappropriately allowed")]
        public void BoolGTDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThan(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on bool and string was inappropriately allowed")]
        public void BoolGTStringOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThan(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on bool and bool was inappropriately allowed")]
        public void BoolGTBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThan(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on string and int was inappropriately allowed")]
        public void StringGTIntOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThan(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on string and decimal was inappropriately allowed")]
        public void StringGTDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThan(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on string and string was inappropriately allowed")]
        public void StringGTStringOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThan(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on string and string was inappropriately allowed")]
        public void StringGTBoolOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThan(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on Int and int was inappropriately allowed")]
        public void IntGTBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345).GreaterThan(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on Int and decimal was inappropriately allowed")]
        public void IntGTStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).GreaterThan(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalGTStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).GreaterThan(ValueCreator.CreateValue("Decimal"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalGTBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45).GreaterThan(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on bool and int was inappropriately allowed")]
        public void BoolGTEIntOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThanOrEqual(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on bool and decimal was inappropriately allowed")]
        public void BoolGTEDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThanOrEqual(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on bool and string was inappropriately allowed")]
        public void BoolGTEStringOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThanOrEqual(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on bool and bool was inappropriately allowed")]
        public void BoolGTEBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).GreaterThanOrEqual(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on string and int was inappropriately allowed")]
        public void StringGTEIntOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThanOrEqual(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on string and decimal was inappropriately allowed")]
        public void StringGTEDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThanOrEqual(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on string and string was inappropriately allowed")]
        public void StringGTEStringOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThanOrEqual(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on string and string was inappropriately allowed")]
        public void StringGTEBoolOperation()
        {
            var result = ValueCreator.CreateValue("string").GreaterThanOrEqual(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on Int and int was inappropriately allowed")]
        public void IntGTEBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345).GreaterThanOrEqual(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on Int and decimal was inappropriately allowed")]
        public void IntGTEStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).GreaterThanOrEqual(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalGTEStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).GreaterThanOrEqual(ValueCreator.CreateValue("Decimal"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Greater than or equal operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalGTEBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45).GreaterThanOrEqual(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on bool and int was inappropriately allowed")]
        public void BoolLTIntOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThan(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on bool and decimal was inappropriately allowed")]
        public void BoolLTDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThan(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on bool and string was inappropriately allowed")]
        public void BoolLTStringOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThan(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on bool and bool was inappropriately allowed")]
        public void BoolLTBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThan(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on string and int was inappropriately allowed")]
        public void StringLTIntOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThan(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on string and decimal was inappropriately allowed")]
        public void StringLTDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThan(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on string and string was inappropriately allowed")]
        public void StringLTStringOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThan(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on string and string was inappropriately allowed")]
        public void StringLTBoolOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThan(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on Int and int was inappropriately allowed")]
        public void IntLTBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345).LessThan(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on Int and decimal was inappropriately allowed")]
        public void IntLTStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).LessThan(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalLTStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).LessThan(ValueCreator.CreateValue("Decimal"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalLTBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45).LessThan(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on bool and int was inappropriately allowed")]
        public void BoolLTEIntOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThanOrEqual(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on bool and decimal was inappropriately allowed")]
        public void BoolLTEDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThanOrEqual(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on bool and string was inappropriately allowed")]
        public void BoolLTEStringOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThanOrEqual(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on bool and bool was inappropriately allowed")]
        public void BoolLTEBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).LessThanOrEqual(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on string and int was inappropriately allowed")]
        public void StringLTEIntOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThanOrEqual(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on string and decimal was inappropriately allowed")]
        public void StringLTEDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThanOrEqual(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on string and string was inappropriately allowed")]
        public void StringLTEStringOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThanOrEqual(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on string and string was inappropriately allowed")]
        public void StringLTEBoolOperation()
        {
            var result = ValueCreator.CreateValue("string").LessThanOrEqual(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on Int and int was inappropriately allowed")]
        public void IntLTEBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345).LessThanOrEqual(ValueCreator.CreateValue(true));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on Int and decimal was inappropriately allowed")]
        public void IntLTEStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).LessThanOrEqual(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalLTEStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).LessThanOrEqual(ValueCreator.CreateValue("Decimal"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Less than or equal operation on Decimal and Decimal was inappropriately allowed")]
        public void DecimalLTEBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45).LessThanOrEqual(ValueCreator.CreateValue(false));
        }
    }
}
