namespace OffByOne.Ql.Tests.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Checker.Analyzers;
    using OffByOne.Ql.Checker.Analyzers.Environment;
    using OffByOne.Ql.Checker.Messages;

    using Xunit;

    public class TypeAnalyzerTests
    {
        public static IEnumerable<object[]> InvalidMathematicalBinaryExpressions => new List<object[]>
        {
            new object[] { new AddExpression(new BooleanLiteral(true), new BooleanLiteral(true)) },
            new object[] { new AddExpression(new IntegerLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new AddExpression(new DecimalLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new AddExpression(new MoneyLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new AddExpression(new IntegerLiteral(1), new BooleanLiteral(true)) },
            new object[] { new AddExpression(new DecimalLiteral(1), new BooleanLiteral(true)) },
            new object[] { new AddExpression(new MoneyLiteral(1), new BooleanLiteral(true)) },
            new object[] { new SubtractExpression(new BooleanLiteral(true), new BooleanLiteral(true)) },
            new object[] { new SubtractExpression(new IntegerLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new SubtractExpression(new DecimalLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new SubtractExpression(new MoneyLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new SubtractExpression(new IntegerLiteral(1), new BooleanLiteral(true)) },
            new object[] { new SubtractExpression(new DecimalLiteral(1), new BooleanLiteral(true)) },
            new object[] { new SubtractExpression(new MoneyLiteral(1), new BooleanLiteral(true)) },
            new object[] { new DivideExpression(new BooleanLiteral(true), new BooleanLiteral(true)) },
            new object[] { new DivideExpression(new IntegerLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new DivideExpression(new DecimalLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new DivideExpression(new MoneyLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new DivideExpression(new IntegerLiteral(1), new BooleanLiteral(true)) },
            new object[] { new DivideExpression(new DecimalLiteral(1), new BooleanLiteral(true)) },
            new object[] { new DivideExpression(new MoneyLiteral(1), new BooleanLiteral(true)) },
            new object[] { new MultiplyExpression(new BooleanLiteral(true), new BooleanLiteral(true)) },
            new object[] { new MultiplyExpression(new IntegerLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new MultiplyExpression(new DecimalLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new MultiplyExpression(new MoneyLiteral(1), new DateLiteral(DateTime.Now)) },
            new object[] { new MultiplyExpression(new IntegerLiteral(1), new BooleanLiteral(true)) },
            new object[] { new MultiplyExpression(new DecimalLiteral(1), new BooleanLiteral(true)) },
            new object[] { new MultiplyExpression(new MoneyLiteral(1), new BooleanLiteral(true)) },
        };

        // TODO: Add more cases
        public static IEnumerable<object[]> InvalidBooleanBinaryExpressions => new List<object[]>
        {
            new object[] { new AndExpression(new BooleanLiteral(true), new IntegerLiteral(1)) },
            new object[] { new AndExpression(new IntegerLiteral(1), new IntegerLiteral(1)) },
        };

        public static IEnumerable<object[]> InvalidBooleanUnaryExpressions => new List<object[]>
        {
            new object[] { new NotExpression(new IntegerLiteral(1)) },
            new object[] { new NotExpression(new DecimalLiteral(1)) },
            new object[] { new NotExpression(new MoneyLiteral(1)) },
            new object[] { new NotExpression(new DateLiteral(DateTime.Today)) },
            new object[] { new NotExpression(new StringLiteral("Hoi!")) }
        };

        public static IEnumerable<object[]> InvalidMathematicalUnaryExpressions => new List<object[]>
        {
            new object[] { new PositiveExpression(new StringLiteral("Need food.")) },
            new object[] { new PositiveExpression(new BooleanLiteral(true)) },
            new object[] { new PositiveExpression(new DateLiteral(DateTime.Now)) },
            new object[] { new NegativeExpression(new StringLiteral("Need more food.")),  },
            new object[] { new NegativeExpression(new BooleanLiteral(true)) },
            new object[] { new NegativeExpression(new DateLiteral(DateTime.Now)) },
        };

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new TypeAnalyzer(null));
        }

        [Theory]
        [MemberData(nameof(InvalidMathematicalBinaryExpressions))]
        [MemberData(nameof(InvalidBooleanBinaryExpressions))]
        [MemberData(nameof(InvalidBooleanUnaryExpressions))]
        [MemberData(nameof(InvalidMathematicalUnaryExpressions))]
        public void Analyze_ShouldDetectInvalidExpressions(Expression sampleExpression)
        {
            var typeAnalyzer = new TypeAnalyzer();

            typeAnalyzer.Visit(sampleExpression, new VisitorTypeEnvironment());

            Assert.NotEmpty(typeAnalyzer.Report.Errors);
            Assert.Equal(1, typeAnalyzer.Report.Errors.Count());
            Assert.IsType<InvalidTypeMessage>(typeAnalyzer.Report.Errors.FirstOrDefault());
        }
    }
}