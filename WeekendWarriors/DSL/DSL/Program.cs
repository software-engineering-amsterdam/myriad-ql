using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


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
                /* a little dumb, but allowed... */
                sellingPrice: ""Price the house was sold for:"" money(------1.0)
                /* Should lead to an error. Cannot initialize money with a number (yet) */
                privateDebt: ""Private debts for the sold house:"" money(1200)
                
            }
            else{
                valueResidue: ""Value residue:"" money (privateDebt - (sellingPrice - privateDebt))
            }

            /* 100 is not a boolean --> Error */
            if(100)
            {
                test: ""Dit is een test"" boolean
            }

            /* Something that we still need to work on: variables within another block should not
                be used outside the block */
            if(sellingPrice < 0.0)
            {
                crappyPrice: ""Were you happy with that?!"" boolean
            }

        }";

            var form = AST.FormFactory.Create(inputString);
            //Console.WriteLine(Newtonsoft.Json.JsonConvert.SerializeObject(form, Formatting.Indented));
            SemanticAnalysis.Analyzer semanticAnalyzer = new SemanticAnalysis.Analyzer();

            semanticAnalyzer.SemanticError += SemanticAnalyzer_SemanticError;

            semanticAnalyzer.Analyze(form);
                
        }

        private static void SemanticAnalyzer_SemanticError(object sender, SemanticAnalysis.SemanticErrorArgs e)
        {
            Console.WriteLine("Semantic error: " + e.Message);
        }
    }
}
