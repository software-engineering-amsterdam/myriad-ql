using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Newtonsoft.Json;

namespace DSL
{
    class Program
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            string inputString = @"
                form Box1HouseOwning {
            hasSoldHouse: ""Did you sell a house in 2010?"" boolean
            hasBoughtHouse: ""Did you by a house in 2010?"" boolean
            hasMaintLoan: ""Did you enter a loan for maintenance / reconstruction ? ""
boolean
            if (hasSoldHouse)
            {
                sellingPrice: ""Price the house was sold for:"" money
                privateDebt: ""Private debts for the sold house:"" money
                
            }
            else{
                valueResidue: ""Value residue:"" money /*(sellingPrice - privateDebt)*/
            }
        }";
            
            AntlrInputStream input = new AntlrInputStream(inputString);
            GrammarLexer  lexer = new GrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GrammarParser parser = new GrammarParser(tokens);
            GrammarParser.FormContext parsetree = parser.form();
            AST.QLVisitor visitor = new AST.QLVisitor();
            var form = visitor.Visit(parsetree);

            Console.WriteLine(Newtonsoft.Json.JsonConvert.SerializeObject(form, Formatting.Indented));

            //Console.WriteLine("ToStringtree:\n" + parsetree.ToStringTree(parser));
            //Console.WriteLine("ToInfoString:\n" + parsetree.ToInfoString ());
            //Console.WriteLine("ToString:\n" + parsetree.ToString());
            //Console.WriteLine("Tokenizer:\n" + lexer.NextToken());
                
        }
    }
}
