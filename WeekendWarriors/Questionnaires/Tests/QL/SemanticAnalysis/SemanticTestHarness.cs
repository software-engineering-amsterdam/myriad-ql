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

        public void TestExpression(string input, uint exprectedErrorCount, string failureMessage)
        {
            QLContext context = new QLContext();
            TypeChecker SemanticAnalyzer = new TypeChecker(context);
            SemanticAnalyzer.SemanticError += SemanticAnalyzer_SemanticError;
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
