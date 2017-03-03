using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.ExpressionEvaluator;
using Questionnaires.VariableStore;
using Questionnaires.AST;
using Questionnaires.Value;
using Tests.QL.Value;
using System.Collections.Generic;

namespace Tests.QL.ExpressionEvaluator
{
    [TestClass]
    public class Arithmatic
    {
        public void TestExpression(Dictionary<string, object> context, string expression, object expectedResult)
        {
            VariableStore store = new VariableStore();
            foreach (var value in context)
                store.SetValue(value.Key, (dynamic)value.Value);

            ASTFactory astFactory = new ASTFactory();
            var parser = astFactory.CreateParser(expression);
            var AST = astFactory.CreateQLObject(parser, ASTFactory.QLObjectType.Expression);

            Evaluator evaluator = new Evaluator(store);
            IValue result = evaluator.Evaluate((IExpression)AST);
            Value.ValueTester.Test((dynamic)result, expectedResult);
        }

        [TestMethod]
        public void Integers()
        {
            Dictionary<string, object> contex = new Dictionary<string, object> {
                { "a", 5 }, { "b", 10 }, { "c", 2 }, { "d", 3 } }
            ;

            TestExpression(contex, "a + b / c * d", 20);
            TestExpression(contex, "(a + b) / (c * d)", 2);
            TestExpression(contex, "(a + b) / c * d", 21);
        }

        [TestMethod]
        public void MixedIntegerAndDecimal()
        {
            Dictionary<string, object> contex = new Dictionary<string, object> {
                { "a", 5 }, { "b", 7.5m }, { "c", 2.5m }, { "d", 8 } }
            ;

            TestExpression(contex, "a + b / c * d", 29m);
            TestExpression(contex, "(a + b) / (c * d)", 0.625m);
            TestExpression(contex, "(a + b) / c * d", 40m);
        }
    }
}
