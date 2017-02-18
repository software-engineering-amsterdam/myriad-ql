using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DSL.AST;

namespace Tests.QL.AST
{
    [TestClass]
    public class Literals
    {
        public ASTFactory formFactory;

        public T ExpressionFromTestCase<T>(string testCase) where T : INode
        {
            var parser = formFactory.CreateParser(testCase);
            return (T)formFactory.CreateQLObject(parser, ASTFactory.QLObjectType.Expression);
        }

        [TestInitialize]
        public void SetupTestFactory()
        {
            formFactory = new ASTFactory();
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
                Assert.AreEqual(
                    ExpressionFromTestCase<QLString>(testCase.Key).Value,
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
                Assert.AreEqual(
                    ExpressionFromTestCase<QLBoolean>(testCase.Key).Value,
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
                { Int32.MaxValue.ToString(), "Test failure: Int32 max" },
                { "2147483648", "Test failure: Int32 max + 1" }
            };

            foreach (var testCase in testCases)
            {
                Assert.AreEqual(
                    ExpressionFromTestCase<QLNumber>(testCase.Key).Value.ToString(),
                    testCase.Key, testCase.Value
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
                { ".123", "Test failure: 0.123" }
            };

            foreach (var testCase in testCases)
            {
                Assert.AreEqual(
                    ExpressionFromTestCase<QLMoney>(testCase.Key).Value,
                    Decimal.Parse(testCase.Key), testCase.Value
                );
            }
        }
    }
}
