using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class InvalidArithmaticOperations
    {
        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on bool and int was inappropriately allowed")]
        public void BoolDivisionIntOperation()
        {
            var result = ValueCreator.CreateValue(true).Divide(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on bool and decimal was inappropriately allowed")]
        public void BoolDivisionDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).Divide(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on bool and string was inappropriately allowed")]
        public void BoolDivisionStringOperation()
        {
            var result = ValueCreator.CreateValue(true).Divide(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on bool and bool was inappropriately allowed")]
        public void BoolDivisionBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).Divide(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on String and int was inappropriately allowed")]
        public void StringDivisionIntOperation()
        {
            var result = ValueCreator.CreateValue("string").Divide(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on String and decimal was inappropriately allowed")]
        public void StringDivisionDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").Divide(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on String and string was inappropriately allowed")]
        public void StringDivisionStringOperation()
        {
            var result = ValueCreator.CreateValue("string").Divide(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on String and bool was inappropriately allowed")]
        public void StringDivisionBoolOperation()
        {
            var result = ValueCreator.CreateValue("String").Divide(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on Decimal and string was inappropriately allowed")]
        public void DecimalDivisionStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45).Divide(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on Decimal and bool was inappropriately allowed")]
        public void DecimalDivisionBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Divide(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on Int and string was inappropriately allowed")]
        public void IntDivisionStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).Divide(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Division operation on Int and bool was inappropriately allowed")]
        public void IntDivisionBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345m).Divide(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on bool and int was inappropriately allowed")]
        public void BoolMultiplicationIntOperation()
        {
            var result = ValueCreator.CreateValue(true).Multiply(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on bool and decimal was inappropriately allowed")]
        public void BoolMultiplicationDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).Multiply(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on bool and string was inappropriately allowed")]
        public void BoolMultiplicationStringOperation()
        {
            var result = ValueCreator.CreateValue(true).Multiply(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on bool and bool was inappropriately allowed")]
        public void BoolMultiplicationBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).Multiply(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on String and int was inappropriately allowed")]
        public void StringMultiplicationIntOperation()
        {
            var result = ValueCreator.CreateValue("string").Multiply(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on String and decimal was inappropriately allowed")]
        public void StringMultiplicationDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").Multiply(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on String and string was inappropriately allowed")]
        public void StringMultiplicationStringOperation()
        {
            var result = ValueCreator.CreateValue("string").Multiply(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on String and bool was inappropriately allowed")]
        public void StringMultiplicationBoolOperation()
        {
            var result = ValueCreator.CreateValue("String").Multiply(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on Decimal and string was inappropriately allowed")]
        public void DecimalMultiplicationStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45).Multiply(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on Decimal and bool was inappropriately allowed")]
        public void DecimalMultiplicationBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Multiply(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on Int and string was inappropriately allowed")]
        public void IntMultiplicationStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).Multiply(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Multiplication operation on Int and bool was inappropriately allowed")]
        public void IntMultiplicationBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345m).Multiply(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on bool and int was inappropriately allowed")]
        public void BoolSubtractionIntOperation()
        {
            var result = ValueCreator.CreateValue(true).Subtract(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on bool and decimal was inappropriately allowed")]
        public void BoolSubtractionDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).Subtract(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on bool and string was inappropriately allowed")]
        public void BoolSubtractionStringOperation()
        {
            var result = ValueCreator.CreateValue(true).Subtract(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on bool and bool was inappropriately allowed")]
        public void BoolSubtractionBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).Subtract(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on String and int was inappropriately allowed")]
        public void StringSubtractionIntOperation()
        {
            var result = ValueCreator.CreateValue("string").Subtract(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on String and decimal was inappropriately allowed")]
        public void StringSubtractionDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").Subtract(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on String and string was inappropriately allowed")]
        public void StringSubtractionStringOperation()
        {
            var result = ValueCreator.CreateValue("string").Subtract(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on String and bool was inappropriately allowed")]
        public void StringSubtractionBoolOperation()
        {
            var result = ValueCreator.CreateValue("String").Subtract(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on Decimal and string was inappropriately allowed")]
        public void DecimalSubtractionStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45).Subtract(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on Decimal and bool was inappropriately allowed")]
        public void DecimalSubtractionBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Subtract(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on Int and string was inappropriately allowed")]
        public void IntSubtractionStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).Subtract(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Subtraction operation on Int and bool was inappropriately allowed")]
        public void IntSubtractionBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345m).Subtract(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on bool and int was inappropriately allowed")]
        public void BoolAdditionIntOperation()
        {
            var result = ValueCreator.CreateValue(true).Add(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on bool and decimal was inappropriately allowed")]
        public void BoolAdditionDecimalOperation()
        {
            var result = ValueCreator.CreateValue(true).Add(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on bool and string was inappropriately allowed")]
        public void BoolAdditionStringOperation()
        {
            var result = ValueCreator.CreateValue(true).Add(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on bool and bool was inappropriately allowed")]
        public void BoolAdditionBoolOperation()
        {
            var result = ValueCreator.CreateValue(true).Add(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on String and int was inappropriately allowed")]
        public void StringAdditionIntOperation()
        {
            var result = ValueCreator.CreateValue("string").Add(ValueCreator.CreateValue(12345));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on String and decimal was inappropriately allowed")]
        public void StringAdditionDecimalOperation()
        {
            var result = ValueCreator.CreateValue("string").Add(ValueCreator.CreateValue(123.45m));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on String and string was inappropriately allowed")]
        public void StringAdditionStringOperation()
        {
            var result = ValueCreator.CreateValue("string").Add(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on String and bool was inappropriately allowed")]
        public void StringAdditionBoolOperation()
        {
            var result = ValueCreator.CreateValue("String").Add(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on Decimal and string was inappropriately allowed")]
        public void DecimalAdditionStringOperation()
        {
            var result = ValueCreator.CreateValue(123.45).Add(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on Decimal and bool was inappropriately allowed")]
        public void DecimalAdditionBoolOperation()
        {
            var result = ValueCreator.CreateValue(123.45m).Add(ValueCreator.CreateValue(false));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on Int and string was inappropriately allowed")]
        public void IntAdditionStringOperation()
        {
            var result = ValueCreator.CreateValue(12345).Add(ValueCreator.CreateValue("string"));
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Addition operation on Int and bool was inappropriately allowed")]
        public void IntAdditionBoolOperation()
        {
            var result = ValueCreator.CreateValue(12345m).Add(ValueCreator.CreateValue(false));
        }
    }
}
