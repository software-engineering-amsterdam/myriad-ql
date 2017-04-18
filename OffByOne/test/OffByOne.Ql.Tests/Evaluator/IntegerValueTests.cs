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

    public class IntegerValueTests
    {
        public static IEnumerable<object[]> SimpleIntegerExpressionData => new List<object[]>
        {
            new object[]
            {
                new AddExpression(new IntegerLiteral(4), new IntegerLiteral(4)),
                typeof(IntegerValue),
                new IntegerValue(8)
            },
            new object[]
            {
                new AddExpression(new IntegerLiteral(4), new NegativeExpression(new IntegerLiteral(4))),
                typeof(IntegerValue),
                new IntegerValue(0)
            },
            new object[]
            {
                new AddExpression(new NegativeExpression(new IntegerLiteral(4)), new IntegerLiteral(4)),
                typeof(IntegerValue),
                new IntegerValue(0)
            },
            new object[]
            {
                new SubtractExpression(new NegativeExpression(new IntegerLiteral(4)), new IntegerLiteral(4)),
                typeof(IntegerValue),
                new IntegerValue(-8)
            },
            new object[]
            {
                new SubtractExpression(new IntegerLiteral(4), new IntegerLiteral(4)),
                typeof(IntegerValue),
                new IntegerValue(0)
            },
            new object[]
            {
                new SubtractExpression(new IntegerLiteral(0), new IntegerLiteral(4)),
                typeof(IntegerValue),
                new IntegerValue(-4)
            },
            new object[]
            {
                new DivideExpression(new IntegerLiteral(0), new IntegerLiteral(4)),
                typeof(IntegerValue),
                new IntegerValue(0)
            },
            new object[]
            {
                new DivideExpression(new IntegerLiteral(2), new IntegerLiteral(2)),
                typeof(IntegerValue),
                new IntegerValue(1)
            },
            new object[]
            {
                new DivideExpression(new IntegerLiteral(3), new IntegerLiteral(2)),
                typeof(IntegerValue),
                new IntegerValue(1)
            },
            new object[]
            {
                new GreaterThanExpression(new IntegerLiteral(3), new IntegerLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanExpression(new IntegerLiteral(2), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanExpression(new IntegerLiteral(3), new IntegerLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanExpression(new IntegerLiteral(2), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new IntegerLiteral(3), new IntegerLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new IntegerLiteral(3), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new IntegerLiteral(2), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new IntegerLiteral(3), new IntegerLiteral(2)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new IntegerLiteral(2), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new IntegerLiteral(3), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new EqualExpression(new IntegerLiteral(3), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new EqualExpression(new IntegerLiteral(2), new IntegerLiteral(3)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new NegativeExpression(new NegativeExpression(new IntegerLiteral(2))),
                typeof(IntegerValue),
                new IntegerValue(2)
            },
            new object[]
            {
                new NegativeExpression(new PositiveExpression(new IntegerLiteral(2))),
                typeof(IntegerValue),
                new IntegerValue(-2)
            }
        };

        [Theory]
        [MemberData(nameof(SimpleIntegerExpressionData))]
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
