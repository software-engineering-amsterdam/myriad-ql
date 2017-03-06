using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System.ComponentModel;

namespace Questionnaires.QL.AST
{
    public class ASTFactory
    {
        public AST.Form CreateForm(string input)
        {
            var parser = CreateParser(input);
            return new ASTBuilder().Visit(parser.form()) as Form;
        }

        public AST.IExpression CreateExpression(string input)
        {
            var parser = CreateParser(input);
            return new ASTBuilder().Visit(parser.expression()) as IExpression;
        }

        public AST.ComputedQuestion CreateComputedQuestion(string input)
        {
            var parser = CreateParser(input);
            return new ASTBuilder().Visit(parser.computedQuestion()) as ComputedQuestion;
        }

        private QLParser CreateParser(string input)
        {
            var inputStream = new AntlrInputStream(input);
            var lexer = new QLLexer(inputStream);
            var tokens = new CommonTokenStream(lexer);
            return new QLParser(tokens);
        }
    }
}
