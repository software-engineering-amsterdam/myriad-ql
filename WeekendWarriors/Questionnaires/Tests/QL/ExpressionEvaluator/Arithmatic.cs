using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST;
using Questionnaires.RunTime;
using System.Collections.Generic;
using Tests.QL.Value;

namespace Tests.QL.ExpressionEvaluator
{
    [TestClass]
    public class Arithmatic
    {
        public void TestExpression(Dictionary<string, object> context, string expression, object expectedResult)
        {
            var questions = new List<Questionnaires.RunTime.Question>();
            foreach (var value in context)
            {
                questions.Add(ValueCreator.CreateQuestion(value.Key, (dynamic)value.Value));
            }

            var store = new QuestionStore(questions);

            var astFactory = new ASTBuilder(new Result());
            var AST = astFactory.BuildExpression(expression);

            var evaluator = new Questionnaires.RunTime.ExpressionEvaluator(store);
            var result = evaluator.Evaluate((IExpression)AST);
            Value.ValueTester.Test((dynamic)result, expectedResult);
        }

        [TestMethod]
        public void Integers()
        {
            var contex = new Dictionary<string, object> {
                { "a", 5 }, { "b", 10 }, { "c", 2 }, { "d", 3 } }
            ;

            TestExpression(contex, "a + b / c * d", 20);
            TestExpression(contex, "(a + b) / (c * d)", 2);
            TestExpression(contex, "(a + b) / c * d", 21);
        }

        [TestMethod]
        public void MixedIntegerAndDecimal()
        {
            var contex = new Dictionary<string, object> {
                { "a", 5 }, { "b", 7.5m }, { "c", 2.5m }, { "d", 8 } }
            ;

            TestExpression(contex, "a + b / c * d", 29m);
            TestExpression(contex, "(a + b) / (c * d)", 0.625m);
            TestExpression(contex, "(a + b) / c * d", 40m);
        }
    }
}
