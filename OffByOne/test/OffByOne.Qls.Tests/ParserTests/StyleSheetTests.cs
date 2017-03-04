namespace OffByOne.Qls.Tests.ParserTests
{
    using System.Collections.Generic;
    using System.Linq;

    using Antlr4.Runtime;

    using OffByOne.Qls.Ast.Style;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Tests.ParserTests.Base;

    using Xunit;

    public class StyleSheetTests : ParserTest
    {
        public static IEnumerable<object[]> SimpleSyntaxisData => new List<object[]>
        {
            new object[] { "stylesheet SampleStyleSheet", "SampleStyleSheet", typeof(StyleSheet) },
            new object[] { "page SamplePage", "SamplePage", typeof(Page) },
            new object[] { "section SampleSection", "SampleSection", typeof(Section) }
        };

        [Fact]
        public void AstCreation_ShouldReturnSingleStyleSheetNodeIfGivenSytaxIsCorrect()
        {
            var astTree = this.GetAstNodesFromInput("stylesheet SampleStyleSheet");
            Assert.IsType<StyleSheet>(astTree);

            var castAstTree = (StyleSheet)astTree;

            Assert.Equal("SampleStyleSheet", castAstTree.Id);
            Assert.Empty(castAstTree.Pages);
        }

        [Fact]
        public void AstCreation_ShouldReturnFullStyleSheetNodeIfGivenSytaxIsCorrect()
        {
            var astTree = this.GetAstNodesFromInput(@"
                stylesheet taxOfficeExample
                  page Housing {
                    section ""Buying"" {
                      question hasBoughtHouse  
                        widget checkbox 
                    }
                    section ""Loaning"" {
                      question hasMaintLoan
                    }
                  } 
                  page Selling { 
                    section ""Selling"" {
                      question hasSoldHouse
                        widget radio(""Yes"", ""No"") 
                      section ""You sold a house"" {
                        question sellingPrice
                          widget spinbox
                        question privateDebt
                          widget spinbox 
                        question valueResidue
                        default money {
                          width: 400
                          font: ""Arial"" 
                          fontsize: 14
                          color: #999999
                          widget spinbox
                        }        
                      }
                    }
                    default boolean widget radio(""Yes"", ""No"")
                  }");

            Assert.IsType<StyleSheet>(astTree);

            var castAstTree = (StyleSheet)astTree;

            Assert.Equal("taxOfficeExample", castAstTree.Id);
            Assert.Equal(2, castAstTree.Pages.Count);

            var sectionsOnFirstPage = castAstTree
                .Pages
                .Where(x => x.Id == "Housing")
                .SelectMany(x => x.Sections)
                .ToList();

            Assert.Equal(2, sectionsOnFirstPage.Count);
            Assert.True(sectionsOnFirstPage.Any(x => x.Name.Value.Value == "Buying"));
            Assert.True(sectionsOnFirstPage.Any(x => x.Name.Value.Value == "Loaning"));

            var assumption = castAstTree.Pages
                .Where(x => x.Id == "Selling")
                .SelectMany(x => x.Sections)
                .Where(x => x.Name.Value.Value == "Selling")
                .SelectMany(x => x.Sections)
                .Where(x => x.Name.Value.Value == "You sold a house")
                .SelectMany(x => x.QuestionRules)
                .Any(x => x.Name == "valueResidue");

            Assert.True(assumption);
        }
    }
}
