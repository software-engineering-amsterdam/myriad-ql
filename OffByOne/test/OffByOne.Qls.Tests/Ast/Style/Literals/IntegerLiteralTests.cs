namespace OffByOne.Qls.Tests.Ast.Style.Literals
{
    using System;
    using System.Drawing;

    using OffByOne.Qls.Ast.Style.Literals;

    using Xunit;

    public class IntegerLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var actual = new IntegerLiteral("42");

            Assert.Equal(42, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentException>(() => new IntegerLiteral(null));
            Assert.Throws<FormatException>(() => new IntegerLiteral("XaX"));
        }
    }
}
