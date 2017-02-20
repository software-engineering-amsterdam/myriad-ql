namespace OffByOne.Runner
{
    using System;

    using Antlr4.Runtime;

    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Ql;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Generated;
    using OffByOne.Qls;

    public class Program
    {
        public static void Main(string[] args)
        {
            TestQlGrammar();
            ////TestQlsGrammar();

            ////var typeChcker = new QlTypeChecker();
            ////typeChcker.CheckTypes(new AddExpression(new BooleanLiteral(true), new IntegerLiteral(2)));
        }

        private static void TestQlGrammar()
        {
            ICharStream input = new AntlrInputStream(@"
                form questionnaire { 
                    if (2 + 3 * 4 < someVar) { 
                        ""Is this a question?""
                            existentialism: boolean
                    }
                }
            ");
            ICharStream input2 = new AntlrInputStream("true or false");
            QlLexer lexer = new QlLexer(input);
            QlParser parser = new QlParser(new CommonTokenStream(lexer));
            var v = new CustomQlVisitor();
            var tree = v.Visit(parser.form());
            Console.WriteLine("Done!");
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

            var lexer = new QlsGrammarLexer(input);
            var parser = new QlsGrammarParser(new CommonTokenStream(lexer));
            var visitor = new CustomQlsVisitor();
            var astTree = visitor.Visit(parser.stylesheet());
            Console.WriteLine("QLS AST conversion done.");
        }
    }
}
