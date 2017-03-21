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
            },
            new object[]
            {
                new SubtractExpression(new NegativeExpression(new MoneyLiteral(4)), new MoneyLiteral(4)),
                typeof(MoneyValue),
                new MoneyValue(-8)
            },
            new object[]
            {
                new SubtractExpression(new MoneyLiteral(4), new MoneyLiteral(4)),
                typeof(MoneyValue),
                new MoneyValue(0)
            },
            new object[]
            {
                new SubtractExpression(new MoneyLiteral(0), new MoneyLiteral(4)),
                typeof(MoneyValue),
                new MoneyValue(-4)
            },
            new object[]
            {
                new DivideExpression(new MoneyLiteral(0), new MoneyLiteral(4)),
                typeof(MoneyValue),
                new MoneyValue(0)
            },
            new object[]
            {
                new DivideExpression(new MoneyLiteral(2), new MoneyLiteral(2)),
                typeof(MoneyValue),
                new MoneyValue(1)
            },
            new object[]
            {
                new DivideExpression(new MoneyLiteral(3), new MoneyLiteral(2)),
                typeof(MoneyValue),
                new MoneyValue(1.5m)
            },
            new object[]
            {
                new GreaterThanExpression(new MoneyLiteral(3), new MoneyLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanExpression(new MoneyLiteral(2), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanExpression(new MoneyLiteral(3), new MoneyLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanExpression(new MoneyLiteral(2), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new MoneyLiteral(3), new MoneyLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new MoneyLiteral(3), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new MoneyLiteral(2), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new MoneyLiteral(3), new MoneyLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new MoneyLiteral(2), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new MoneyLiteral(3), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new EqualExpression(new MoneyLiteral(3), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new EqualExpression(new MoneyLiteral(2), new MoneyLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new NegativeExpression(new NegativeExpression(new MoneyLiteral(2))),
                typeof(MoneyValue),
                new MoneyValue(2)
            },
            new object[]
            {
                new NegativeExpression(new PositiveExpression(new MoneyLiteral(2))),
                typeof(MoneyValue),
                new MoneyValue(-2)
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
