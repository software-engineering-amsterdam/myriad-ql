namespace OffByOne.Ql.Tests.Ast.Literals
{
    using System;

    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Values;

    using Xunit;

    public class BooleanLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var expected = new BooleanValue(true);
            var actual = new BooleanLiteral("true");

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new BooleanLiteral(null));
            Assert.Throws<FormatException>(() => new BooleanLiteral("XaX"));
        }
    }
}
