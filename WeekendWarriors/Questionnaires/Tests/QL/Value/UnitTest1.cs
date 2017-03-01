using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.Value;

namespace Tests.QL.Value
{

    [TestClass]
    public class Values
    {

        void TestAnd(bool leftHandSideOperand, bool rightHandSideOperand, bool expectedResult)
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).And(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic)result, expectedResult);
        }

        void TestOr(bool leftHandSideOperand, bool rightHandSideOperand, bool expectedResult)
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).Or(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic)result, expectedResult);
        }

        void TestBang(bool Operand, bool expectedResult)
        {
            var result = ValueCreator.CreateValue(Operand).Bang();
            ValueTester.Test((dynamic)result, expectedResult);
        }

        void TestEquality<T1, T2, RT>(T1 leftHandSideOperand, T2 rightHandSideOperand, RT expectedResult)
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).EqualTo(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic)result, expectedResult);
        }

        void TestInequality<T1, T2, RT>(T1 leftHandSideOperand, T2 rightHandSideOperand, RT expectedResult)
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).InequalTo(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic)result, expectedResult);
        }

        void TestAddition<T1, T2, RT>(T1 leftHandSideOperand, T2 rightHandSideOperand, RT expextedResult) 
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).Add(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic) result, expextedResult);
        }

        void TestSubtraction<T1, T2, RT>(T1 leftHandSideOperand, T2 rightHandSideOperand, RT expextedResult)
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).Subtract(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic)result, expextedResult);
        }

        void TestMultiplication<T1, T2, RT>(T1 leftHandSideOperand, T2 rightHandSideOperand, RT expextedResult)
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).Multiply(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic)result, expextedResult);
        }

        void TestDivision<T1, T2, RT>(T1 leftHandSideOperand, T2 rightHandSideOperand, RT expextedResult)
        {
            var result = ValueCreator.CreateValue(leftHandSideOperand).Divide(ValueCreator.CreateValue(rightHandSideOperand));
            ValueTester.Test((dynamic)result, expextedResult);
        } 

        [TestMethod]
        public void ValidBangOperation()
        {
            TestBang(true, false);
            TestBang(false, true);
        }

        [TestMethod]
        public void ValidAnd()
        {
            TestAnd(true, true, true);
            TestAnd(true, false, false);
            TestAnd(false, true, false);
            TestAnd(false, false, false);
        }

        [TestMethod]
        public void ValidOr()
        {
            TestOr(true, true, true);
            TestOr(true, false, true);
            TestOr(false, true, true);
            TestOr(false, false, false);
        }

        [TestMethod]
        public void ValidEquality()
        {
            // Boolean
            TestEquality(true, true, true);
            TestEquality(true, false, false);
            TestEquality(false, true, false);
            TestEquality(false, false, true);
            // Numer
            TestEquality(10, 10, true);
            TestEquality(10, 20, false);
            // Decimal 
            TestEquality(10m, 10m, true);
            TestEquality(10m, 20m, false);
            // Combination of number and decimal
            TestEquality(10, 10m, true);
            TestEquality(20m, 20, true);
            TestEquality(10, 20m, false);
            TestEquality(10m, 20, false);
            // Strings
            TestEquality("some string", "some string", true);
            TestEquality("some string", "Some string", false);
            TestEquality("some string", " some string", false);
        }

        [TestMethod]
        public void ValidInequality()
        {
            TestInequality(true, true, false);
            TestInequality(true, false, true);
            TestInequality(false, true, true);
            TestInequality(false, false, false);
            // Numer
            TestInequality(10, 10, false);
            TestInequality(10, 20, true);
            // Decimal 
            TestInequality(10m, 10m, false);
            TestInequality(10m, 20m, true);
            // Combination of number and decimal
            TestInequality(10, 10m, false);
            TestInequality(20m, 20, false);
            TestInequality(10, 20m, true);
            TestInequality(10m, 20, true);
            // Strings
            TestInequality("some string", "some string", false);
            TestInequality("some string", "Some string", true);
            TestInequality("some string", " some string", true);
        }

        [TestMethod]
        public void ValidAddition()
        {
            // integer addition
            TestAddition(10, 15, 25);
            // Integer and decimal addition
            TestAddition(10, 15m, 25m);
            TestAddition(10m, 15, 25m);
            // Decimal addition
            TestAddition(10m, 15m, 25m);
        }

        [TestMethod]
        public void ValidSubtraction()
        {
            // integer subtraction
            TestSubtraction(10, 15, -5);
            // Integer and decimal subtraction
            TestSubtraction(10, 15m, -5m);
            TestSubtraction(60m, 15, 45m);
            // Decimal subtraction
            TestSubtraction(100m, 75m, 25m);
        }

        [TestMethod]
        public void ValidMultiplication()
        {
            // integer multiplication
            TestMultiplication(10, 15, 150);
            // Integer and decimal multiplication
            TestMultiplication(10, 15m, 150m);
            TestMultiplication(3.5m, 4, 14m);
            // Decimal multiplication
            TestMultiplication(4.0m, 3.5m, 14m);
        }

        [TestMethod]
        public void ValidDivision()
        {
            // integer multiplication
            TestDivision(150, 15, 10);
            // Integer and decimal multiplication
            TestDivision(150, 15m, 10m);
            TestDivision(14m, 4, 3.5m);
            // Decimal multiplication
            TestDivision(14m, 3.5m, 4m);
        }
    }
}
