namespace OffByOne.Runner
{
    using System;
    using System.Threading;
    using System.Windows;

    using Antlr4.Runtime;

    using OffByOne.Ql.Ast.Statements;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Generated;
    using OffByOne.Ql.Graphics;
    using OffByOne.Ql.Interpreter;

    using OffByOne.Qls;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Checker;

    using AstCreator = OffByOne.Qls.Ast.AstCreator;

    public class Program : Application
    {
        [STAThread]
        public static void Main(string[] args)
        {
            ////TestQlGrammar();
            ////TestQlsGrammar();
            TestInterpreter();

            ////var typeChcker = new TypeChecker();
            ////var testCondition = new IfStatement(
            ////    new EqualExpression(
            ////        new BooleanLiteral(true),
            ////        new IntegerLiteral(2)),
            ////    new List<Statement>(),
            ////    new List<Statement>());

            ////var report = typeChcker.Check(new FormStatement(
            ////    "test",
            ////    new List<Statement> { testCondition }));

            ////foreach (var message in report.AllMessages)
            ////{
            ////    Console.WriteLine(message);
            ////}

            ////Console.WriteLine("Type check done!");

            ////var test = 3 + "Test";

            ////var evaluator = new Evaluator();
            ////var expression = new AddExpression(new StringLiteral("Test"), new StringLiteral("Hex"));
            ////Console.WriteLine(evaluator.Evaluate(expression, new TypeEnvironment()));
        }

        private static void TestInterpreter()
        {
            var interpreter = new Interpreter();
            ICharStream input = new AntlrInputStream(@"
                form questionnaire { 
                    ""String one:""
                        stringone: string

                    ""String two:""
                        stringtwo: string

                    ""Concat:""
                        concat: string (stringone + stringtwo)
                }
            ");
            QlLexer lexer = new QlLexer(input);
            QlParser parser = new QlParser(new CommonTokenStream(lexer));
            var typeEnv = new TypeEnvironment();
            var env = new GuiEnvironment(typeEnv);
            var astCreator = new Ql.Ast.AstCreator();
            var tree = (FormStatement)astCreator.Visit(parser.form());
            var form = interpreter.Visit(tree, env) as Window;
            var application = new System.Windows.Application();
            application.Run(form);
        }

        private static void TestQlGrammar()
        {
            ICharStream input = new AntlrInputStream(@"
                form questionnaire { 
                    ""Is this a question?""
                        existentialism: boolean

                    ""Is this a question?""
                        existentialism: boolean

                    ""When will this be finished?""
                        deadline: date

                    ""What is your favourite colour?""
                        deadline: string

                    ""What is your favourite number?""
                        deadline: integer

                    ""How much does one beer cost?""
                        deadline: money

                    ""What is your favourite decimal number?""
                        deadline: decimal
                }
            ");
            ICharStream input2 = new AntlrInputStream("true or false");
            QlLexer lexer = new QlLexer(input);
            QlParser parser = new QlParser(new CommonTokenStream(lexer));
            var v = new Ql.Ast.AstCreator();
            var tree = v.Visit(parser.form());
            CheckTypes((FormStatement)tree);
            Console.WriteLine("Done!");
            var eval = new Renderer();
            eval.Visit((FormStatement)tree, new VisitorTypeEnvironment());
        }

        private static void TestQlsGrammar()
        {
            var input = new AntlrInputStream(@"
                stylesheet taxOfficeExample
                  page Housing {
                    section ""Buying"" {
                      question hasBoughtHouse  
                        widget checkbox 
                    }
                    section ""Loaning"" {
                      question hasMaintLoan
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

            ////var lexer = new QlsGrammarLexer(input);
            ////var parser = new QlsGrammarParser(new CommonTokenStream(lexer));
            ////var visitor = new AstCreator();
            ////var astTree = visitor.Visit(parser.stylesheet());
            ////CheckTypes((StyleSheet)astTree);
            ////Console.WriteLine("QLS AST conversion done.");
        }

        private static void CheckTypes(FormStatement ast)
        {
            var typeChcker = new TypeChecker();
            var report = typeChcker.Check(ast);

            foreach (var message in report.AllMessages)
            {
                Console.WriteLine(message);
            }

            Console.WriteLine("Type check done!");
        }

        private static void CheckTypes(StyleSheet ast)
        {
            var typeChcker = new StyleSheetAnalyzer();
            var report = typeChcker.Check(ast);

            foreach (var message in report.AllMessages)
            {
                Console.WriteLine(message);
            }

            Console.WriteLine("Type check done!");
        }
    }
}
