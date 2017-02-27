using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.AST;

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
                    CreateASTNode<Questionnaires.AST.Literals.String>(testCase.Key).Value,
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
                    CreateASTNode<Questionnaires.AST.Literals.Boolean>(testCase.Key).Value,
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
                { Int32.MaxValue.ToString(), "Test failure: Int32 max" }
            };

            foreach (var testCase in testCases)
            {
                Assert.AreEqual(
                    CreateASTNode<Questionnaires.AST.Literals.Number>(testCase.Key).Value.ToString(),
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
                    CreateASTNode<Questionnaires.AST.Literals.Money>(testCase.Key).Value,
                    Decimal.Parse(testCase.Key), testCase.Value
                );
            }
        }
    }
}
