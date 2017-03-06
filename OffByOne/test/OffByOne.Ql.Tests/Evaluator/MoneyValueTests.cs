namespace OffByOne.Ql.Tests.Evaluator
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Values.Contracts;

    using Xunit;

    public class MoneyValueTests
    {
        public static IEnumerable<object[]> SimpleMoneyExpressionData => new List<object[]>
        {
            new object[]
            {
                new AddExpression(new MoneyLiteral(4), new MoneyLiteral(4)),
                typeof(MoneyValue),
                new MoneyValue(8),
            },
            new object[]
            {
                new AddExpression(new MoneyLiteral(4), new NegativeExpression(new MoneyLiteral(4))),
                typeof(MoneyValue),
                new MoneyValue(0)
            },
            new object[]
            {
                new AddExpression(new NegativeExpression(new MoneyLiteral(4)), new MoneyLiteral(4)),
                typeof(MoneyValue),
                new MoneyValue(0)
            }
        };

        [Theory]
        [MemberData(nameof(SimpleMoneyExpressionData))]
        public void Evaluate_ShoudlReturnExpectedValueFromExpression(
            Expression expression,
            Type expectedResultType,
            IValue expected)
        {
            var evaluator = new Evaluator();
            var environment = new TypeEnvironment();
            var result = evaluator.Evaluate(expression, environment);
            Assert.IsType(expectedResultType, result);
            Assert.NotNull(result);
            Assert.Equal(expected, result);
        }
    }
}
