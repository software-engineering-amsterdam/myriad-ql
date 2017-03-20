using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.QL.AST;
using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.ErrorHandling;

namespace Tests.QL.SemanticAnalysis
{
    public class SemanticTestHarness
    {
        protected uint ErrorCount = 0;
        protected ASTBuilder ASTFactory = new ASTBuilder(new Result());

        public void TestExpression(string input, int exprectedErrorCount, string failureMessage)
        {
            var result = new Result();
            var SemanticAnalyzer = new ExposedSemanticAnalyzer(result);
            var node = ASTFactory.BuildExpression(input);
            SemanticAnalyzer.AnalyzeAstNode(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }

        public void TestForm(string input, int exprectedErrorCount, string failureMessage)
        {
            var result = new Result();
            var SemanticAnalyzer = new SemanticAnalyzer(result);
            var node = ASTFactory.BuildForm(input);
            SemanticAnalyzer.AnalyzeForm(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }

        public void TestComputedQuestion(string input, int exprectedErrorCount, string failureMessage)
        {
            var result = new Result();
            var SemanticAnalyzer = new ExposedSemanticAnalyzer(result);
            var node = ASTFactory.BuildComputedQuestion(input);
            SemanticAnalyzer.AnalyzeAstNode(node);

            Assert.AreEqual(exprectedErrorCount, result.Events.Count, failureMessage);
        }
    }
}
