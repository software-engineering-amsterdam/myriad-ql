namespace OffByOne.Ql.Tests.Evaluator
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Evaluator;

    using Xunit;

    public class BooleanValueTests
    {
        public static IEnumerable<object[]> SimpleBooleanExpressionData => new List<object[]>
        {
            new object[]
            {
                new AndExpression(new BooleanLiteral(true), new BooleanLiteral(true)), "True"
            },
            new object[]
            {
                new AndExpression(new BooleanLiteral(false), new BooleanLiteral(true)), "False"
            },
            new object[]
            {
                new AndExpression(new BooleanLiteral(true), new BooleanLiteral(false)), "False"
            },
            new object[]
            {
                new AndExpression(new BooleanLiteral(false), new BooleanLiteral(false)), "False"
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(true), new BooleanLiteral(true)), "True"
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(false), new BooleanLiteral(true)), "True"
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(true), new BooleanLiteral(false)), "True"
            },
            new object[]
            {
                new OrExpression(new BooleanLiteral(false), new BooleanLiteral(false)), "False"
            },
            new object[]
            {
                new NotExpression(new BooleanLiteral(false)), "True"
            },
            new object[]
            {
                new NotExpression(new BooleanLiteral(true)), "False"
            },
            new object[]
            {
                new EqualExpression(new BooleanLiteral(false), new BooleanLiteral(false)), "True"
            },
            new object[]
            {
                new EqualExpression(new BooleanLiteral(true), new BooleanLiteral(false)), "False"
            },
        };

        [Theory]
        [MemberData(nameof(SimpleBooleanExpressionData))]
        public void Evaluate_ShoudlReturnExpectedValueFromExpression(
            Expression expression,
            string expected)
        {
            var evaluator = new Evaluator();
            var environment = new TypeEnvironment();
            var result = evaluator.Evaluate(expression, environment).ToString();
            Assert.NotNull(result);
            Assert.Equal(expected, result);
        }
    }
}
