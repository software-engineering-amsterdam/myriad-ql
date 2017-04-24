namespace OffByOne.Qls.Tests.Checker
{
    using System;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker;

    using Xunit;

    public class StyleSheetCheckerTests
    {
        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new StyleSheetChecker(null));
        }

        [Fact]
        public void Check_ShouldThrowExceptionIfNullStructureNodeIsGiven()
        {
            var styleSheetChecker = new StyleSheetChecker();
            Assert.Throws<ArgumentNullException>(() => styleSheetChecker.Check(null, new StyleSheet("SampleSheet")));
        }

        [Fact]
        public void Check_ShouldThrowExceptionIfNullStyleNodeIsGiven()
        {
            var styleSheetChecker = new StyleSheetChecker();
            Assert.Throws<ArgumentNullException>(() => styleSheetChecker.Check(new FormStatement("SampleForm"), null));
        }
    }
}
