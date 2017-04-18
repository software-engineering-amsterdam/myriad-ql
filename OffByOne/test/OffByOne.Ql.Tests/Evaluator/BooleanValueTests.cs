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

    public class BooleanValueTests
    {
        public static IEnumerable<object[]> SimpleBooleanExpressionData => new List<object[]>
        {
            new object[]
            {
                new AndExpression(new BooleanLiteral(true), new BooleanLiteral(true)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new AndExpression(new BooleanLiteral(false), new BooleanLiteral(true)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new AndExpression(new BooleanLiteral(true), new BooleanLiteral(false)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new AndExpression(new BooleanLiteral(false), new BooleanLiteral(false)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(true), new BooleanLiteral(true)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(false), new BooleanLiteral(true)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(true), new BooleanLiteral(false)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(false), new BooleanLiteral(false)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new NotExpression(new BooleanLiteral(false)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new NotExpression(new BooleanLiteral(true)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new EqualExpression(new BooleanLiteral(false), new BooleanLiteral(false)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new EqualExpression(new BooleanLiteral(true), new BooleanLiteral(false)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
        };

        [Theory]
        [MemberData(nameof(SimpleBooleanExpressionData))]
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
