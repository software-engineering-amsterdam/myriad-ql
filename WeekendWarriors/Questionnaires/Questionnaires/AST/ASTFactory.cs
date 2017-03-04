using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System.ComponentModel;

namespace Questionnaires.AST
{
    public class ASTFactory
    {
        public INode CreateForm(string input)
        {
            var parser = CreateParser(input);
            return new ASTBuilder().Visit(parser.form());
        }

        public INode CreateExpression(string input)
        {
            var parser = CreateParser(input);
            return new ASTBuilder().Visit(parser.expression());
        }

        public INode CreateComputedQuestion(string input)
        {
            var parser = CreateParser(input);
            return new ASTBuilder().Visit(parser.computedQuestion());
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
