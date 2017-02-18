using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

namespace DSL.AST
{
    public class FormFactory
    {
        // TODO: Tijs is not a fan of static functions. Let's do some research to see if we
        // can defend the choice or need to cave and use non-static
        public GrammarParser CreateParser(string input)
        {
            AntlrInputStream inputStream = new AntlrInputStream(input);
            GrammarLexer lexer = new GrammarLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            return new GrammarParser(tokens);
        }

        public INode CreateForm(GrammarParser parser)
        {
            GrammarParser.FormContext parsetree = parser.form();
            QLVisitor visitor = new QLVisitor();
            return visitor.Visit(parsetree);
        }

        public INode CreateExpression(GrammarParser parser)
        {
            GrammarParser.ExpressionContext parsetree = parser.expression();
            QLVisitor visitor = new QLVisitor();
            return visitor.Visit(parsetree);
        }
    }
}
