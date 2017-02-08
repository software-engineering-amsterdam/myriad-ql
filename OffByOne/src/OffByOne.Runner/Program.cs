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
            QlLexer lexer = new QlLexer(input);
            QlParser parser = new QlParser(new CommonTokenStream(lexer));
            var v = new MyQlVisitor();
            AstNode tree = v.Visit(parser.form());
            Console.WriteLine("Done!");
        }
    }
}
