namespace OffByOne.Qls.Tests.Ast.Style.Statements
{
    using System;

    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;

    using Xunit;

    public class PageTests
    {
        [Theory]
        [InlineData(null)]
        [InlineData("")]
        [InlineData("          ")]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven(string samplePageName)
        {
            Assert.Throws<ArgumentException>(() => new Page(samplePageName, null, null));
        }
    }
}
