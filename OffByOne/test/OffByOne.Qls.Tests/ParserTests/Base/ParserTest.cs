namespace OffByOne.Qls.Tests.ParserTests.Base
{
    using Antlr4.Runtime;

    using OffByOne.LanguageCore.Ast;

    public abstract class ParserTest
    {
        protected AstNode GetAstNodesFromInput(string input)
        {
            var charStream = new AntlrInputStream(input);
            var lexer = new QlsGrammarLexer(charStream);
            var parser = new QlsGrammarParser(new CommonTokenStream(lexer));
            var visitor = new CustomQlsVisitor();
            return visitor.Visit(parser.stylesheet());
        }
    }
}
