using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.QL.AST;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires;

namespace Tests.QL.SemanticAnalysis
{
    public class SemanticTestHarness
    {
        protected uint ErrorCount = 0;
        protected ASTFactory ASTFactory = new ASTFactory();
        
        public void TestExpression(string input, int exprectedErrorCount, string failureMessage)
        {
            ExposedSemanticAnalyzer SemanticAnalyzer = new ExposedSemanticAnalyzer();            
            var node = ASTFactory.CreateExpression(input);
            var result = SemanticAnalyzer.AnalyzeAstNode(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }

        public void TestForm(string input, int exprectedErrorCount, string failureMessage)
        {
            SemanticAnalyzer SemanticAnalyzer = new SemanticAnalyzer();
            var node = ASTFactory.CreateForm(input, new Questionnaires.Compilation.Result());
            var result = SemanticAnalyzer.AnalyzeForm(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }

        public void TestComputedQuestion(string input, int exprectedErrorCount, string failureMessage)
        {
            ExposedSemanticAnalyzer SemanticAnalyzer = new ExposedSemanticAnalyzer();
            var node = ASTFactory.CreateComputedQuestion(input);
            var result = SemanticAnalyzer.AnalyzeAstNode(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }
    }
}
