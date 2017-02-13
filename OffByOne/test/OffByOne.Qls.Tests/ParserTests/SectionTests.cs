namespace OffByOne.Qls.Tests.ParserTests
{
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;
    using MoreDotNet.Extensions.Common;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style;
    using OffByOne.Qls.Tests.ParserTests.Base;

    using Xunit;

    public class SectionTests : ParserTest
    {
        [Fact]
        public void AstCreation_ShouldReturnTrueIfSectionNodesParsedCorrectly()
        {
            var astTree = this.GetAstNodesFromInput(@"
                stylesheet fuckingShit
                    page Page1 {
                        section ""Section1"" {
                        }
                        section ""Section2"" {
                        }
                    }
                    page Page2 {
                        section ""Section3"" {
                        }
                        section ""Section4"" {
                        }
                    }");

            Assert.IsType<StyleSheet>(astTree);

            var sectionNodes = this.GetAllSectionNodes((StyleSheet)astTree);

            Assert.Equal(4, sectionNodes.Count);

            for (int i = 1; i < 5; i++)
            {
                Assert.Equal("Section" + i, sectionNodes[i - 1].Name.Value.Trim('"'));
            }
        }

        private IList<Section> GetAllSectionNodes(StyleSheet tree)
        {
            var nodes = tree.Pages
                .SelectMany(x => x.Nodes
                    .Select(y => (Section)y))
                .OrderBy(x => x.Name.Value)
                .ToList();

            return nodes;
        }
    }
}
