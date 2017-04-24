namespace OffByOne.Qls.Tests.Ast.Style.Literals
{
    using System;

    using OffByOne.Qls.Ast.Style.Literals;

    using Xunit;

    public class DecimalLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorrectDataGiven()
        {
            var actual = new DecimalLiteral("0.66");

            Assert.Equal(0.66, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentException>(() => new DecimalLiteral(null));
            Assert.Throws<FormatException>(() => new DecimalLiteral("XaX"));
        }
    }
}
