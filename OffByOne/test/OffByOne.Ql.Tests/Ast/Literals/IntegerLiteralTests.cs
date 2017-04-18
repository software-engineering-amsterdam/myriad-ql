namespace OffByOne.Ql.Tests.Ast.Literals
{
    using System;

    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Values;

    using Xunit;

    public class IntegerLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var expected = new IntegerValue(79);
            var actual = new IntegerLiteral("79");

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new IntegerLiteral(null));
            Assert.Throws<FormatException>(() => new IntegerLiteral("XaX"));
        }
    }
}
