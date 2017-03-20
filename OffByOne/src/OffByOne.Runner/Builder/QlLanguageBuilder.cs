namespace OffByOne.Runner.Builder
{
    using System;
    using System.Windows;
    using System.Windows.Controls;

    using Antlr4.Runtime;

    using MoreDotNet.Extensions.Common;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Generated;
    using OffByOne.Ql.Interpreter;
    using OffByOne.Runner.Builder.Contracts;

    public class QlLanguageBuilder : IQlLanguageBuilder
    {
        public QlLanguageBuilder(string inputData)
        {
            this.InputData = inputData;
        }

        public string InputData { get; }

        public FormStatement BuildAst()
        {
            var dataStream = new AntlrInputStream(this.InputData);
            var lexer = new QlLexer(dataStream);
            var parser = new QlParser(new CommonTokenStream(lexer));
            var astCreator = new AstCreator();
            var astTree = astCreator.Visit(parser.form());

            Console.WriteLine("Ql AST building done!");

            return astTree.As<FormStatement>();
        }

        public ICheckerReport CheckSyntax(FormStatement root)
        {
            var typeChecker = new SyntaxChecker();
            var report = typeChecker.Check(root);

            foreach (var message in report.AllMessages)
            {
                Console.WriteLine(message);
            }

            Console.WriteLine("QL Type check done!");
            return report;
        }

        public void RunApplication(FormStatement structureNode)
        {
            var interpreter = new Interpreter();
            var env = new GuiEnvironment();
            var form = interpreter.Visit(structureNode, env);
            var window = new Window();
            var view = new ListView();
            foreach (var control in form.Controls)
            {
                view.Items.Add(control);
            }

            window.Content = view;
            var application = new Application();
            application.Run(window);
        }
    }
}