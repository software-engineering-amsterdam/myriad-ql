using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

namespace DSL.AST
{
    public class ASTFactory
    {
        public enum QLObjectType
        {
            Form,
            Statement,
            ComputedQuestion,
            Question,
            Conditional,
            Composite,
            Expression
        }

        public GrammarParser CreateParser(string input)
        {
            AntlrInputStream inputStream = new AntlrInputStream(input);
            GrammarLexer lexer = new GrammarLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            return new GrammarParser(tokens);
        }

        public INode CreateQLObject(GrammarParser parser, QLObjectType qlObjectType)
        {
            QLVisitor visitor = new QLVisitor();

            switch (qlObjectType)
            {
                case QLObjectType.Form:
                    return visitor.Visit(parser.form());
                case QLObjectType.Statement:
                    return visitor.Visit(parser.statement());
                case QLObjectType.ComputedQuestion:
                    return visitor.Visit(parser.computedQuestion());
                case QLObjectType.Question:
                    return visitor.Visit(parser.question());
                case QLObjectType.Conditional:
                    return visitor.Visit(parser.conditionalBlock());
                case QLObjectType.Composite:
                    return visitor.Visit(parser.composite());
                case QLObjectType.Expression:
                default:
                    return visitor.Visit(parser.expression());
            }
        }
    }
}
