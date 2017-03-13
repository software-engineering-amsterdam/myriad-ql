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
        protected ASTBuilder ASTFactory = new ASTBuilder(new Questionnaires.Compilation.Result());
        
        public void TestExpression(string input, int exprectedErrorCount, string failureMessage)
        {
            Questionnaires.Compilation.Result result = new Questionnaires.Compilation.Result();
            ExposedSemanticAnalyzer SemanticAnalyzer = new ExposedSemanticAnalyzer(result);            
            var node = ASTFactory.BuildExpression(input);
            SemanticAnalyzer.AnalyzeAstNode(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }

        public void TestForm(string input, int exprectedErrorCount, string failureMessage)
        {
            Questionnaires.Compilation.Result result = new Questionnaires.Compilation.Result();
            SemanticAnalyzer SemanticAnalyzer = new SemanticAnalyzer(result);
            var node = ASTFactory.BuildForm(input);
            SemanticAnalyzer.AnalyzeForm(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }

        public void TestComputedQuestion(string input, int exprectedErrorCount, string failureMessage)
        {
            Questionnaires.Compilation.Result result = new Questionnaires.Compilation.Result();
            ExposedSemanticAnalyzer SemanticAnalyzer = new ExposedSemanticAnalyzer(result);
            var node = ASTFactory.BuildComputedQuestion(input);
            SemanticAnalyzer.AnalyzeAstNode(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }
    }
}
