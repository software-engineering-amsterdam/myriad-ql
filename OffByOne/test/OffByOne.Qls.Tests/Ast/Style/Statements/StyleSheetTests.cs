namespace OffByOne.Qls.Tests.Ast.Style.Statements
{
    using System;

    using OffByOne.Qls.Ast.Style.Statements;

    using Xunit;

    public class StyleSheetTests
    {
        [Theory]
        [InlineData(null)]
        [InlineData("")]
        [InlineData("          ")]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven(string sampleStyleSheetName)
        {
            Assert.Throws<ArgumentException>(() => new StyleSheet(sampleStyleSheetName, null));
        }
    }
}
