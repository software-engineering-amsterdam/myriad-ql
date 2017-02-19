using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DSL.AST;

namespace Tests.QL.AST
{
    [TestClass]
    public class Literals
    {
        public ASTFactory astFactory;

        public T CreateASTNode<T>(string input) where T : INode
        {
            var parser = astFactory.CreateParser(input);
            return (T)astFactory.CreateQLObject(parser, ASTFactory.QLObjectType.Expression);
        }

        [TestInitialize]
        public void SetupTestFactory()
        {
            astFactory = new ASTFactory();
        }
        
        [TestMethod]
        public void StringLiteral()
        {
            var testCases = new Dictionary<string, string>
            {
                { "\"\"", "Test failure: Empty string" },
                { "\"PieceOfText\"", "Test failure: Single word string" },
                { "\"Piece Of Text\"", "Test failure: Multi word string" },
                { "\"\"\"\"", "Test failure: Escape characters" }
            };
            
            foreach (var testCase in testCases)
            {
                Assert.AreEqual(
                    CreateASTNode<QLString>(testCase.Key).Value,
                    testCase.Key, testCase.Value
                );
            }
        }

        [TestMethod]
        public void BooleanLiteral()
        {
            var testCases = new Dictionary<string, string>
            {
                { "true", "Test failure: True literal" },
                { "false", "Test failure: False literal" }
            };

            foreach (var testCase in testCases)
            {
                Assert.AreEqual(
                    CreateASTNode<QLBoolean>(testCase.Key).Value,
                    Boolean.Parse(testCase.Key), testCase.Value
                );
            }
        }

        [TestMethod]
        public void NumberLiteral()
        {
            var testCases = new Dictionary<string, string>
            {
                { "123456", "Test failure: 123456 literal" },
                { "0", "Test failure: 0 literal" },
                { Int32.MaxValue.ToString(), "Test failure: Int32 max" },
                { "2147483648", "Test failure: Int32 max + 1" }
            };

            foreach (var testCase in testCases)
            {
                Assert.AreEqual(
                    CreateASTNode<QLNumber>(testCase.Key).Value.ToString(),
                    testCase.Key, testCase.Value
                );
            }
        }

        [TestMethod]
        public void MoneyLiteral()
        {
            var testCases = new Dictionary<string, string>
            {
                { "123.456", "Test failure: 123.456 literal" },
                { "0.", "Test failure: 0. literal" },
                { ".123", "Test failure: 0.123" }
            };

            foreach (var testCase in testCases)
            {
                Assert.AreEqual(
                    CreateASTNode<QLMoney>(testCase.Key).Value,
                    Decimal.Parse(testCase.Key), testCase.Value
                );
            }
        }
    }
}
