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

    public class DateValueTests
    {
        public static IEnumerable<object[]> SimpleDateExpressionData => new List<object[]>
        {
            new object[]
            {
                new EqualExpression(new DateLiteral(DateTime.Today.Date), new DateLiteral(DateTime.Today.Date)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new NotEqualExpression(new DateLiteral(DateTime.Now), new DateLiteral(DateTime.Today.Date)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanExpression(new DateLiteral(DateTime.Now), new DateLiteral(DateTime.Now.AddDays(-20))),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new LessThanExpression(new DateLiteral(DateTime.Now), new DateLiteral(DateTime.Now.AddDays(-20))),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new GreaterThanExpression(new DateLiteral(DateTime.Now.AddDays(-20)), new DateLiteral(DateTime.Now)),
                typeof(BooleanValue),
                new BooleanValue(false)
            },
            new object[]
            {
                new LessThanExpression(new DateLiteral(DateTime.Now.AddDays(-20)), new DateLiteral(DateTime.Now)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new LessThanOrEqualExpression(new DateLiteral(DateTime.Today.Date), new DateLiteral(DateTime.Today.Date)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
            new object[]
            {
                new GreaterThanOrEqualExpression(new DateLiteral(DateTime.Today.Date), new DateLiteral(DateTime.Today.Date)),
                typeof(BooleanValue),
                new BooleanValue(true)
            },
        };

        [Theory]
        [MemberData(nameof(SimpleDateExpressionData))]
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
