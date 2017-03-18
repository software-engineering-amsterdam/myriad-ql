using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Numerics;

namespace Tests.QL.SemanticAnalysis
{
    [TestClass]
    public class Ranges
    {
        protected SemanticTestHarness TestHarness = new SemanticTestHarness();

        [TestMethod]
        public void NumberRange()
        {
            TestHarness.TestComputedQuestion(
                "question : \"body\" int(" + int.MaxValue + ")",
                0,
                "Using the maximal allowed value");

            TestHarness.TestComputedQuestion(
                "question : \"body\" int(" + (int.MaxValue + (long)1).ToString() + ")",
                1,
                "using a number that is too large");

            TestHarness.TestComputedQuestion(
               "question : \"body\" int(" + int.MinValue + ")",
               0,
               "Using the minimal allowed value");

            TestHarness.TestComputedQuestion(
                "question : \"body\" int(" + (int.MinValue - (long)1).ToString() + ")",
                1,
                "Using a number that is too small");
        }

        [TestMethod]
        public void MoneyRange()
        {
            TestHarness.TestComputedQuestion(
                "question : \"body\" money(" + decimal.MaxValue.ToString() + ".0)",
                0,
                "Using the maximal allowed value");

            TestHarness.TestComputedQuestion(
               "question : \"body\" money(" + decimal.MinValue.ToString() + ".0)",
               0,
               "Using the minimal allowed value");

            var overflow = new BigInteger(decimal.MaxValue);
            overflow++;
            var underflow = new BigInteger(Decimal.MinValue);
            underflow--;

            TestHarness.TestComputedQuestion(
                "question : \"body\" money(" + overflow.ToString() + ".0)",
                1,
                "using a number that is too large");            

            TestHarness.TestComputedQuestion(
                "question : \"body\" money(" + underflow.ToString() + ".0)",
                1,
                "Using a number that is too small");
        }
    }
}
