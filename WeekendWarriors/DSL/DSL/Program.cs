using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


using Newtonsoft.Json;
using DSL.AST;

namespace DSL
{
    class Program
    {
        static void Main(string[] args)
        {
            string inputString = "form Box1HouseOwning { hasSoldHouse: \"Did you sell a house in 2010?\" boolean }";

            var formFactory = new AST.ASTFactory();
            var parser = formFactory.CreateParser(inputString);
            var form = formFactory.CreateForm(parser);

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
