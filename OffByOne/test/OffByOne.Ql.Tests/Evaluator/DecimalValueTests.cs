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

    public class DecimalValueTests
    {
        public static IEnumerable<object[]> SimpleDecimalExpressionData => new List<object[]>
        {
            new object[]
            {
                new AddExpression(new DecimalLiteral(4), new DecimalLiteral(4)),
                typeof(DecimalValue),
                new DecimalValue(8),
            },
            new object[]
            {
                new AddExpression(new DecimalLiteral(4), new NegativeExpression(new DecimalLiteral(4))),
                typeof(DecimalValue),
                new DecimalValue(0)
            },
            new object[]
            {
                new AddExpression(new NegativeExpression(new DecimalLiteral(4)), new DecimalLiteral(4)),
                typeof(DecimalValue),
                new DecimalValue(0)
            },
            new object[]
            {
                new SubtractExpression(new NegativeExpression(new DecimalLiteral(4)), new DecimalLiteral(4)),
                typeof(DecimalValue),
                new DecimalValue(-8)
            },
            new object[]
            {
                new SubtractExpression(new DecimalLiteral(4), new DecimalLiteral(4)),
                typeof(DecimalValue),
                new DecimalValue(0)
            },
            new object[]
            {
                new SubtractExpression(new DecimalLiteral(0), new DecimalLiteral(4)),
                typeof(DecimalValue),
                new DecimalValue(-4)
            },
            new object[]
            {
                new DivideExpression(new DecimalLiteral(0), new DecimalLiteral(4)),
                typeof(DecimalValue),
                new DecimalValue(0)
            },
            new object[]
            {
                new DivideExpression(new DecimalLiteral(4), new DecimalLiteral(0)),
                typeof(DecimalValue),
                new DecimalValue(double.PositiveInfinity)
            },
            new object[]
            {
                new DivideExpression(new DecimalLiteral(2), new DecimalLiteral(2)),
                typeof(DecimalValue),
                new DecimalValue(1)
            },
            new object[]
            {
                new DivideExpression(new DecimalLiteral(3), new DecimalLiteral(2)),
                typeof(DecimalValue),
                new DecimalValue(1.5)
            },
            new object[]
            {
                new GreaterThanExpression(new DecimalLiteral(3), new DecimalLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanExpression(new DecimalLiteral(2), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanExpression(new DecimalLiteral(3), new DecimalLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanExpression(new DecimalLiteral(2), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new DecimalLiteral(3), new DecimalLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new DecimalLiteral(3), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new DecimalLiteral(2), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new DecimalLiteral(3), new DecimalLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new DecimalLiteral(2), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new DecimalLiteral(3), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new EqualExpression(new DecimalLiteral(3), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new EqualExpression(new DecimalLiteral(2), new DecimalLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new NegativeExpression(new NegativeExpression(new DecimalLiteral(2))),
                typeof(DecimalValue),
                new DecimalValue(2)
            },
            new object[]
            {
                new NegativeExpression(new PositiveExpression(new DecimalLiteral(2))),
                typeof(DecimalValue),
                new DecimalValue(-2)
            }
        };

        [Theory]
        [MemberData(nameof(SimpleDecimalExpressionData))]
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
