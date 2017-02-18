using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DSL.AST;

namespace Tests.QL.AST
{
    [TestClass]
    public class Literals
    {
        public FormFactory formFactory;

        [TestInitialize]
        public void SetupTestFactory()
        {
            formFactory = new FormFactory();
        }

        [TestMethod]
        public void StringLiteral()
        {
            Dictionary<string, string> testCases = new Dictionary<string, string>
            {
                { "\"\"", "Test failure: Empty string" },
                { "\"PieceOfText\"", "Test failure: Single word string" },
                { "\"Piece Of Text\"", "Test failure: Multi word string" },
                { "\"\"\"\"", "Test failure: Escape characters" }
            };
            
            foreach (var testCase in testCases)
            {
                var parser = formFactory.CreateParser(testCase.Key);
                var expression = (QLString)formFactory.CreateExpression(parser);

                Assert.AreEqual(
                    expression.Value,
                    testCase.Key, testCase.Value
                );
            }
        }

        [TestMethod]
        public void BooleanLiteral()
        {
            Dictionary<string, string> testCases = new Dictionary<string, string>
            {
                { "true", "Test failure: True literal" },
                { "false", "Test failure: False literal" }
            };

            foreach (var testCase in testCases)
            {
                var parser = formFactory.CreateParser(testCase.Key);
                var expression = (QLBoolean)formFactory.CreateExpression(parser);

                Assert.AreEqual(
                    expression.Value,
                    Boolean.Parse(testCase.Key), testCase.Value
                );
            }
        }

        [TestMethod]
        public void NumberLiteral()
        {
            Dictionary<string, string> testCases = new Dictionary<string, string>
            {
                { "123456", "Test failure: 123456 literal" },
                { "0", "Test failure: 0 literal" },
                { Int32.MaxValue.ToString(), "Test failure: Int32 max" }
            };

            foreach (var testCase in testCases)
            {
                var parser = formFactory.CreateParser(testCase.Key);
                var expression = (QLNumber)formFactory.CreateExpression(parser);

                Assert.AreEqual(
                    expression.Value,
                    Int32.Parse(testCase.Key), testCase.Value
                );
            }
        }

        [TestMethod]
        public void MoneyLiteral()
        {
            Dictionary<string, string> testCases = new Dictionary<string, string>
            {
                { "123.456", "Test failure: 123.456 literal" },
                { "0.", "Test failure: 0. literal" },
                { "0.123", "Test failure: 0.123" }
            };

            foreach (var testCase in testCases)
            {
                var parser = formFactory.CreateParser(testCase.Key);
                var expression = (QLMoney)formFactory.CreateExpression(parser);

                Assert.AreEqual(
                    expression.Value,
                    Decimal.Parse(testCase.Key), testCase.Value
                );
            }
        }
    }
}
