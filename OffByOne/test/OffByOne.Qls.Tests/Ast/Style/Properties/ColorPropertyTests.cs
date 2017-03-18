namespace OffByOne.Qls.Tests.Ast.Style.Properties
{
    using System;
    using System.Drawing;

    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Properties;

    using Xunit;

    public class ColorPropertyTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueIfCorectDataGiven()
        {
            var actual = new ColorProperty(new HexLiteral(Color.Violet));

            Assert.Equal(Color.Violet, actual.Value.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new ColorProperty(null));
        }
    }
}
