namespace OffByOne.Ql.Tests.Evaluator
{
    using System.Collections.Generic;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Evaluator;

    using Xunit;

    public class StringEvaluationTestes
    {
        public static IEnumerable<object[]> SimpleSyntaxisData => new List<object[]>
        {
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new StringLiteral("World")), "HelloWorld"
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new IntegerLiteral(2)), "Hello2"
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new DecimalLiteral(2.0)), "Hello2"
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new DecimalLiteral(2.1)), "Hello2.1"
            },
            new object[]
            {
                new AddExpression(new StringLiteral("Hello"), new MoneyLiteral(2.1m)), "Hello2.1"
            }
        };

        [Theory]
        [MemberData(nameof(SimpleSyntaxisData))]
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
