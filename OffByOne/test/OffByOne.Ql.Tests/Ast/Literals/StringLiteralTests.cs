namespace OffByOne.Ql.Tests.Ast.Literals
{
    using System;

    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Values;

    using Xunit;

    public class StringLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var expected = new StringValue("HelloWorld");
            var actual = new StringLiteral("HelloWorld");

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldTrimUnwantedSymbols()
        {
            var expected = new StringValue("HelloWorld");
            var actual = new StringLiteral("\"HelloWorld\"");

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new StringLiteral(null));
        }
    }
}
