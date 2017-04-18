namespace OffByOne.Runner.Builder
{
    using System;

    using Antlr4.Runtime;

    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Qls;
    using OffByOne.Qls.Ast;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker;
    using OffByOne.Runner.Builder.Contracts;

    public class QlsLanguageBuilder : IQlsLanguageBuilder
    {
        public QlsLanguageBuilder(string inputData)
        {
            this.InputData = inputData;
        }

        public string InputData { get; }

        public StyleSheet BuildAst()
        {
            var dataStream = new AntlrInputStream(this.InputData);
            var lexer = new QlsGrammarLexer(dataStream);
            var parser = new QlsGrammarParser(new CommonTokenStream(lexer));
            var visitor = new AstCreator();
            var astTree = visitor.Visit(parser.stylesheet());

            Console.WriteLine("Ql AST building done!");

            return astTree.As<StyleSheet>();
        }

        public void CheckSyntax(FormStatement structureRoot, StyleSheet styleRoot)
        {
            var typeChcker = new StyleSheetChecker();
            var report = typeChcker.Check(structureRoot, styleRoot);

            foreach (var message in report.AllMessages)
            {
                Console.WriteLine(message);
            }

            Console.WriteLine("QLS Type check done!");
        }

        public void RunApplication(FormStatement structureNode, StyleSheet styleNode)
        {
            Console.WriteLine("Not implemented.");
        }
    }
}