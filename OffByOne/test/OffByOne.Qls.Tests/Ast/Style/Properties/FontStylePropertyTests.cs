namespace OffByOne.Qls.Tests.Ast.Style.Properties
{
    using System;

    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Properties;

    using Xunit;

    public class FontStylePropertyTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueIfCorectDataGiven()
        {
            var actual = new FontStyleProperty(new StringLiteral("Fancy"));

            Assert.Equal("Fancy", actual.Value.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new FontStyleProperty(null));
        }
    }
}
