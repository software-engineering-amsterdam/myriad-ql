namespace OffByOne.Qls.Tests.Ast.Style.Properties
{
    using System;

    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Properties;

    using Xunit;

    public class FontSizePropertyTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueIfCorectDataGiven()
        {
            var actual = new FontSizeProperty(new IntegerLiteral(42));

            Assert.Equal(42, actual.Value.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new FontSizeProperty(null));
        }
    }
}
