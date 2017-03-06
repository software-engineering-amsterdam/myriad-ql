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
