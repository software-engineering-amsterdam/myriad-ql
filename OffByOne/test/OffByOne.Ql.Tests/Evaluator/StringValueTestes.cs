namespace OffByOne.Ql.Tests.Evaluator
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Values.Contracts;

    using Xunit;

    public class StringValueTestes
    {
        public static IEnumerable<object[]> SimpleStringExpressionData => new List<object[]>
        {
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new StringLiteral("World")),
                typeof(StringValue),
                new StringValue("HelloWorld")
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new IntegerLiteral(2)),
                typeof(StringValue),
                new StringValue("Hello2")
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new DecimalLiteral(2.0)),
                typeof(StringValue),
                new StringValue("Hello2")
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new DecimalLiteral(2.1)),
                typeof(StringValue),
                new StringValue("Hello2.1")
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new MoneyLiteral(2.1m)), typeof(StringValue), new StringValue("Hello2.1")
            },
            new object[]
            {
                new EqualExpression(new StringLiteral("Hello"), new StringLiteral("Hello")),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new NotEqualExpression(new StringLiteral("Hello"), new StringLiteral("Hello")),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new EqualExpression(new StringLiteral("Hello"), new StringLiteral("HelloX")),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new NotEqualExpression(new StringLiteral("Hello"), new StringLiteral("HelloX")),
                typeof(BooleanValue),
                new BooleanValue(true)
            }
        };

        [Theory]
        [MemberData(nameof(SimpleStringExpressionData))]
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
