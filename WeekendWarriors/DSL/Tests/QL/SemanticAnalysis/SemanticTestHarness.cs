using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.SemanticAnalysis;
using DSL.AST;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.SemanticAnalysis
{
    public class SemanticTestHarness
    {
        protected Analyzer SemanticAnalyzer = new Analyzer();
        protected uint ErrorCount = 0;
        protected ASTFactory ASTFactory = new ASTFactory();
        protected ASTFactory.QLObjectType Type;

        public SemanticTestHarness(ASTFactory.QLObjectType type)
        {
            SemanticAnalyzer.SemanticError += SemanticAnalyzer_SemanticError;
            Type = type;
        }

        public void TestExpression(string input, uint exprectedErrorCount, string failureMessage)
        {
            ErrorCount = 0;
            var parser = ASTFactory.CreateParser(input);
            var node = ASTFactory.CreateQLObject(parser, Type);
            SemanticAnalyzer.Analyze(node);

            Assert.AreEqual(exprectedErrorCount, ErrorCount, failureMessage);
        }

        private void SemanticAnalyzer_SemanticError(object sender, SemanticErrorArgs e)
        {
            ErrorCount++;
        }
    }
}
