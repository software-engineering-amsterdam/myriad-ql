namespace OffByOne.Ql.Tests.Ast.Literals
{
    using System;
    using System.Drawing;

    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Values;

    using Xunit;

    public class HexLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var expected = new StringValue("#000000");
            var actual = new HexLiteral(Color.Black);

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentException>(() => new HexLiteral(null));
        }
    }
}
