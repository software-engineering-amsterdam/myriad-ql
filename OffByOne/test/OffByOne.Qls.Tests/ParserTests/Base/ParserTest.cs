namespace OffByOne.Qls.Tests.ParserTests.Base
{
    using Antlr4.Runtime;

    using OffByOne.Ql.Ast;

    using AstCreator = OffByOne.Qls.Ast.AstCreator;

    public abstract class ParserTest
    {
        protected AstNode GetAstNodesFromInput(string input)
        {
            var charStream = new AntlrInputStream(input);
            var lexer = new QlsGrammarLexer(charStream);
            var parser = new QlsGrammarParser(new CommonTokenStream(lexer));
            var visitor = new AstCreator();
            return visitor.Visit(parser.stylesheet());
        }
    }
}
