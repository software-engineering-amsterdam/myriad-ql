namespace OffByOne.Ql.Tests.Ast.Literals
{
    using System;

    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Values;

    using Xunit;

    public class DecimalLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var expected = new DecimalValue(79.9);
            var actual = new DecimalLiteral("79.9");

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new DecimalLiteral(null));
            Assert.Throws<FormatException>(() => new DecimalLiteral("XaX"));
        }
    }
}
