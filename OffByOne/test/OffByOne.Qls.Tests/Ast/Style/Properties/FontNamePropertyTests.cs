namespace OffByOne.Qls.Tests.Ast.Style.Properties
{
    using System;

    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Properties;

    using Xunit;

    public class FontNamePropertyTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueIfCorectDataGiven()
        {
            var actual = new FontNameProperty(new StringLiteral("HelloWorld!"));

            Assert.Equal("HelloWorld!", actual.Value.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new FontNameProperty(null));
        }
    }
}
