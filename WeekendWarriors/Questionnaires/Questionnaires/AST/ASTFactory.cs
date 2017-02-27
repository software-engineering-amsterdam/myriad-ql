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

        public QLParser CreateParser(string input)
        {
            AntlrInputStream inputStream = new AntlrInputStream(input);
            QLLexer lexer = new QLLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            return new QLParser(tokens);
        }

        public INode CreateQLObject(QLParser parser, QLObjectType qlObjectType)
        {
            ASTBuilder builder = new ASTBuilder();

            switch (qlObjectType)
            {
                case QLObjectType.Form:
                    return builder.Visit(parser.form());
                case QLObjectType.Statement:
                    return builder.Visit(parser.statement());
                case QLObjectType.ComputedQuestion:
                    return builder.Visit(parser.computedQuestion());
                case QLObjectType.Question:
                    return builder.Visit(parser.question());
                case QLObjectType.Conditional:
                    return builder.Visit(parser.conditionalBlock());
                case QLObjectType.Composite:
                    return builder.Visit(parser.composite());
                case QLObjectType.Expression:                
                    return builder.Visit(parser.expression());
                default:
                    throw new InvalidEnumArgumentException();
            }
        }
    }
}
