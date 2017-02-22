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
            string inputString = @"
                form myForm{
                    q1: ""This is q1"" boolean
                    if(q2)
                    {
                        q3: ""This is q3"" boolean
                    }
                }
                ";

            var formFactory = new AST.ASTFactory();
            var parser = formFactory.CreateParser(inputString);
            var form = formFactory.CreateQLObject(parser, ASTFactory.QLObjectType.Form);

            var semanticAnalyzer = new SemanticAnalysis.Analyzer();

            semanticAnalyzer.SemanticError += ReportSemanticError;
            semanticAnalyzer.Analyze(form);
        }

        private static void ReportSemanticError(object sender, SemanticAnalysis.SemanticErrorArgs e)
        {
            Console.WriteLine("Semantic error: " + e.Message);
        }
    }
}
