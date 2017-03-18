namespace OffByOne.Qls.Tests.Ast.Style.Literals
{
    using System;
    using System.Drawing;

    using OffByOne.Qls.Ast.Style.Literals;

    using Xunit;

    public class HexLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var expected = ColorTranslator.FromHtml("#FF0000");
            var actual = new HexLiteral("#FF0000");

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentException>(() => new HexLiteral(null));
            Assert.Throws<Exception>(() => new HexLiteral("XaX"));
        }
    }
}
