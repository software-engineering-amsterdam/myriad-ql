using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.QL.AST;
using Questionnaires.SemanticAnalysis;

namespace Tests.QL.SemanticAnalysis
{
    [TestClass]
    public class ArithmaticOperations
    {
        protected SemanticTestHarness TestHarness = new SemanticTestHarness();

        [TestMethod]
        public void TestNumberAddition()
        {
            TestHarness.TestExpression("1 + 1", 0, "Standard number addition");
            TestHarness.TestExpression("1 + 2 + 3", 0, "Triple number addition" );
            TestHarness.TestExpression("(1+1)+1", 0, "Number addition with brackets" );
            TestHarness.TestExpression("1 + true", 1, "Adding a number and a boolean" );
            TestHarness.TestExpression("1 + 1.123 ", 0, "Adding a number and a money" );
            TestHarness.TestExpression("1 + \"string\"", 1, "Adding a number and a string" );
        }

        [TestMethod]
        public void TestDecimalAddition()
        {
            TestHarness.TestExpression("1.23 + 2.34", 0, "Standard money addition");
            TestHarness.TestExpression("1.23 + 2.34 + 3.45", 0, "Triple money addition");
            TestHarness.TestExpression("(1.23+2.34)+3.45", 0, "money addition with brackets");
            TestHarness.TestExpression("1.23 + true", 1, "Adding a money and a boolean");
            TestHarness.TestExpression("1.23 + 1 ", 0, "Adding a money and a number");
            TestHarness.TestExpression("1.23 + \"string\"", 1, "Adding a money and a string");
        }

        [TestMethod]
        public void TestInvalidAddition()
        {
            TestHarness.TestExpression("true + false", 1, "Invalid addition: bool plus bool");
            TestHarness.TestExpression("false + \"string\"", 1, "Invalid addition: bool plus string");
            // Appending to a string with the plus operator is not supported
            TestHarness.TestExpression("\"string\" + \"some other string\"", 1, "Invalid addition: adding strings");
        }

        [TestMethod]
        public void TestNumberSubtraction()
        {
            TestHarness.TestExpression("1 - 1", 0, "Standard number subtraction");
            TestHarness.TestExpression("1 - 2 - 3", 0, "Triple number subtraction");
            TestHarness.TestExpression("(1-1)-1", 0, "Number subtraction with brackets");
            TestHarness.TestExpression("1 - true", 1, "Subtracting a boolean from a number");
            TestHarness.TestExpression("1 - 1.123", 0, "Subtracting a money from number");
            TestHarness.TestExpression("1 - \"string\"", 1, "Adding a number and a string");
        }

        [TestMethod]
        public void TestDecimalSubtraction()
        {
            TestHarness.TestExpression("1.23 - 2.34", 0, "Standard money subtraction");
            TestHarness.TestExpression("1.23 - 2.34 - 3.45", 0, "Triple money subtraction");
            TestHarness.TestExpression("(1.23-2.34)-3.45", 0, "money subtraction with brackets");
            TestHarness.TestExpression("1.23 - true", 1, "Subtracting a boolean from a money");
            TestHarness.TestExpression("1.234 - 1", 0, "Subtracting a money from money");
            TestHarness.TestExpression("1.23 - \"string\"", 1, "Adding a money and a string");
        }

        [TestMethod]
        public void TestInvalidSubtraction()
        {
            TestHarness.TestExpression("true - false", 1, "Invalid subtraction: bool minus bool");
            TestHarness.TestExpression("false - \"string\"", 1, "Invalid subtraction: bool minus string");
            TestHarness.TestExpression("true - 1", 1, "Invalid subtraction: bool minus number");
            TestHarness.TestExpression("false - 1.23", 1, "Invalid subtraction: bool minus decimal");

            TestHarness.TestExpression("\"string\" - \"some other string\"", 1, "Invalid subtraction: subtracting strings");
            TestHarness.TestExpression("\"string\" - false", 1, "Invalid subtraction: string minus bool");
            TestHarness.TestExpression("\"string\" - 1", 1, "Invalid subtraction: string minus number");
            TestHarness.TestExpression("\"string\" - 1.23", 1, "Invalid subtraction: string minus decimal");
        }

        [TestMethod]
        public void TestNumberMultiplication()
        {
            TestHarness.TestExpression("1 * 1", 0, "Standard number multiplication");
            TestHarness.TestExpression("1 * 2 * 3", 0, "Triple number multiplication");
            TestHarness.TestExpression("(1*1)*1", 0, "Number multiplication with brackets");
            TestHarness.TestExpression("1 * true", 1, "Multiplying a boolean with a number");
            TestHarness.TestExpression("45 * false", 1, "Multiplying a number with a boolean");
            TestHarness.TestExpression("1 * 1.23", 0, "Multiplying a number with money");
            TestHarness.TestExpression("1 * \"string\"", 1, "Multiplying a number and a string");
        }

        [TestMethod]
        public void TestDecimalMultiplication()
        {
            TestHarness.TestExpression("1.23 * 2.34", 0, "Standard money multiplication");
            TestHarness.TestExpression("1.23 * 2.34 * 3.45", 0, "Triple money multiplication");
            TestHarness.TestExpression("(1.23*2.34)*3.45", 0, "money multiplication with brackets");
            TestHarness.TestExpression("1.23 * true", 1, "Multiplying a money and a boolean");;
            TestHarness.TestExpression("1.234 * 1", 0, "Multiplying a money and a number");
            TestHarness.TestExpression("1.23 * \"string\"", 1, "Multiplying a money and a string");
        }

        [TestMethod]
        public void TestInvalidMultiplication()
        {
            TestHarness.TestExpression("true * false", 1, "Invalid multiplication: bool and bool");
            TestHarness.TestExpression("false * \"string\"", 1, "Invalid multiplication: bool and string");
            TestHarness.TestExpression("true * 1", 1, "Invalid multiplication: bool and number");
            TestHarness.TestExpression("false * 1.23", 1, "Invalid multiplication: bool and decimal");

            TestHarness.TestExpression("\"string\" * \"some other string\"", 1, "Invalid multiplication: multiplying strings");
            TestHarness.TestExpression("\"string\" * false", 1, "Invalid multiplication: string and bool");
            TestHarness.TestExpression("\"string\" * 1", 1, "Invalid multiplication: string and number");
            TestHarness.TestExpression("\"string\" * 1.23", 1, "Invalid multiplication: string and decimal");
        }

        [TestMethod]
        public void TestNumberDivision()
        {
            TestHarness.TestExpression("1 / 1", 0, "Standard number division");
            TestHarness.TestExpression("1 / 2 / 3", 0, "Triple number division");
            TestHarness.TestExpression("(1/1)/1", 0, "Number division with brackets");
            TestHarness.TestExpression("1 / true", 1, "Dividing a boolean by a number");
            TestHarness.TestExpression("45 / false", 1, "Dividing a number by a boolean");
            TestHarness.TestExpression("1 / 1.23", 0, "Dividing a number by a money");
            TestHarness.TestExpression("1 / \"string\"", 1, "Dividing a number by a string");
        }

        [TestMethod]
        public void TestDecimalDivision()
        {
            TestHarness.TestExpression("1.23 / 2.34", 0, "Standard money division");
            TestHarness.TestExpression("1.23 / 2.34 / 3.45", 0, "Triple money division");
            TestHarness.TestExpression("(1.23/2.34)/3.45", 0, "money division with brackets");
            TestHarness.TestExpression("1.23 / true", 1, "Dividing a money by a boolean"); ;
            TestHarness.TestExpression("1.234 / 1", 0, "Dividing a money by a number");
            TestHarness.TestExpression("1.23 / \"string\"", 1, "Dividing a money by a string");
        }

        [TestMethod]
        public void TestInvalidDivision()
        {
            TestHarness.TestExpression("true / false", 1, "Invalid division: bool and bool");
            TestHarness.TestExpression("false / \"string\"", 1, "Invalid division: bool and string");
            TestHarness.TestExpression("true / 1", 1, "Invalid division: bool and number");
            TestHarness.TestExpression("false / 1.23", 1, "Invalid division: bool and decimal");

            TestHarness.TestExpression("\"string\" / \"some other string\"", 1, "Invalid division: multiplying strings");
            TestHarness.TestExpression("\"string\" / false", 1, "Invalid division: string and bool");
            TestHarness.TestExpression("\"string\" / 1", 1, "Invalid division: string and number");
            TestHarness.TestExpression("\"string\" / 1.23", 1, "Invalid division: string and decimal");
        }
    }
}
