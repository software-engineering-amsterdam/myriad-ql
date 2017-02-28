using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.AST;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires;

namespace Tests.QL.SemanticAnalysis
{
    public class SemanticTestHarness
    {
        protected uint ErrorCount = 0;
        protected ASTFactory ASTFactory = new ASTFactory();
        protected ASTFactory.QLObjectType Type;

        public SemanticTestHarness(ASTFactory.QLObjectType type)
        {
            this.Type = type;
        }

        public void TestExpression(string input, int exprectedErrorCount, string failureMessage)
        {
            SemanticAnalyzer SemanticAnalyzer = new SemanticAnalyzer();            
            var parser = ASTFactory.CreateParser(input);
            var node = ASTFactory.CreateQLObject(parser, Type);
            var result = SemanticAnalyzer.Analyze(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }
    }
}
