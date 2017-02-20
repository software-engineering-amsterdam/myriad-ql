namespace OffByOne.Qls.Tests.ParserTests
{
    using System.Linq;

    using OffByOne.Qls.Ast.Style;
    using OffByOne.Qls.Tests.ParserTests.Base;

    using Xunit;

    public class PageTests : ParserTest
    {
        [Fact]
        public void AstCreation_ShouldReturnStyleNodeWithPagesIfCorrectInputIsGiven()
        {
            var astTree = this.GetAstNodesFromInput(@"
                stylesheet fuckingShit
                    page Page1 {}
                    page Page2 {}");

            Assert.IsType<StyleSheet>(astTree);

            var castAstTree = (StyleSheet)astTree;

            Assert.Equal("fuckingShit", castAstTree.Id);
            Assert.Equal(2, castAstTree.Pages.Count);
            Assert.True(castAstTree.Pages
                .Select(x => x.Nodes.Count == 0)
                .Aggregate((x, y) => x && y));

            Assert.Equal("Page1", castAstTree.Pages.ElementAt(0).Id);
            Assert.Equal("Page2", castAstTree.Pages.ElementAt(1).Id);
        }
    }
}
