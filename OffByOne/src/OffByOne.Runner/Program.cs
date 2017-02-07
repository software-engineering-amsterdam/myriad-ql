namespace OffByOne.Runner
{
    using System;
    using Antlr4.Runtime;
    using OffByOne.LanguageCore.Ast;
    using OffByOne.Ql;
    using OffByOne.Ql.Generated;

    public class Program
    {
        public static void Main(string[] args)
        {
            ICharStream input = new AntlrInputStream("form questionaire { \"I haz question?\" myQuestion: boolean if true { \"a\" b: string } else { \"c\" d: money } }");
            QlLexer lexer = new QlLexer(input);
            QlParser parser = new QlParser(new CommonTokenStream(lexer));
            var v = new MyQlVisitor();
            AstNode tree = v.Visit(parser.form());
            Console.WriteLine("Done!");
        }
    }
}
