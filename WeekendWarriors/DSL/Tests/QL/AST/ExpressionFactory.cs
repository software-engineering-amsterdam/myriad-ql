using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.AST;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

namespace Tests.QL.AST
{
    class ExpressionFactory
    {
        // TODO: this is largely a duplicate of the formfactory. That kind of smells.
        public static INode Create(string input)
        {
            AntlrInputStream inputStream = new AntlrInputStream(input);
            GrammarLexer lexer = new GrammarLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GrammarParser parser = new GrammarParser(tokens);
            GrammarParser.ExpressionContext parsetree = parser.expression();
            QLVisitor visitor = new QLVisitor();
            return visitor.Visit(parsetree);
        }
    }
}
