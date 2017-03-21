namespace OffByOne.Ql.Tests.Parser.Base
{
    using Antlr4.Runtime;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Generated;

    public abstract class ParserTest
    {
        protected AstNode GetAstNodesFromInput(string input)
        {
            var charStream = new AntlrInputStream(input);
            var lexer = new QlLexer(charStream);
            var parser = new QlParser(new CommonTokenStream(lexer));
            var visitor = new AstCreator();
            return visitor.Visit(parser.form());
        }
    }
}
