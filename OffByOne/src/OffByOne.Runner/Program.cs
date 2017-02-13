namespace OffByOne.Runner
{
    using System;

    using Antlr4.Runtime;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.Ql;
    using OffByOne.Ql.Generated;
    using OffByOne.Qls;

    public class Program
    {
        public static void Main(string[] args)
        {
            ////TestQlGrammar();
            TestQlsGrammar();
        }

        private static void TestQlGrammar()
        {
            ICharStream input = new AntlrInputStream(@"
                form questionaire { 
                    ""What is your birth date?"" 
                        birthDate: date

                    ""Do you want to continue?""
                        continue: boolean

                    if birthDate < '01-01-2000' and continue { 
                        ""How much money do you spend on alcoholic beverages?""
                            alcoholicBeverages: money
                    } else if continue {
                        ""How much money do you spend on soda?""
                            soda: money 
                    } else {
                        ""Okay. Goodbye?""
                            exit: boolean
                    }
                }");
            var lexer = new QlLexer(input);
            var parser = new QlParser(new CommonTokenStream(lexer));
            var v = new MyQlVisitor();
            var tree = v.Visit(parser.form());
            Console.WriteLine("Done!");
        }

        private static void TestQlsGrammar()
        {
            ICharStream input = new AntlrInputStream(@"
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
