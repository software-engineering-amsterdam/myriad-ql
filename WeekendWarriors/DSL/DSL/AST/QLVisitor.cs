using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime.Misc;

namespace DSL.AST
{
    class QLVisitor : GrammarBaseVisitor<INode>
    {
        public override INode VisitForm([NotNull] GrammarParser.FormContext context)
        {            
            string identifier = context.Identifier().GetText();
            QLForm form = new QLForm(identifier);
            GrammarParser.StatementContext[] statements = context.statement();
            foreach(GrammarParser.StatementContext statement in statements)
            {
                form.AddStatement(Visit(statement));
            }
            return form;
        }

        public override INode VisitQuestion([NotNull] GrammarParser.QuestionContext context)
        {
            string identifier = context.Identifier().GetText();
            string body = context.StringLiteral().GetText();
            string type = context.Type().GetText();
            return new QLQuestion(identifier, body, type);
        }

        public override INode VisitConditionalBlock([NotNull] GrammarParser.ConditionalBlockContext context)
        {            
            INode expression = Visit(context.condition());
            INode thenStatements = Visit(context.thenBlock());
            INode elseStatements = null;
            if (context.elseBlock() != null)
            {
                elseStatements = Visit(context.elseBlock());
            }

            return new QLConditional(expression, thenStatements, elseStatements);
            throw new NotImplementedException();
        }

        public override INode VisitThenBlock([NotNull] GrammarParser.ThenBlockContext context)
        {
            QLStatementBlock statementBlock = new QLStatementBlock();
            GrammarParser.StatementContext[] statements = context.statement();
            foreach (GrammarParser.StatementContext statement in statements)
            {
                statementBlock.AddStatement(Visit(statement));
            }
            return statementBlock;
        }

        public override INode VisitElseBlock([NotNull] GrammarParser.ElseBlockContext context)
        {
            QLStatementBlock statementBlock = new QLStatementBlock();
            GrammarParser.StatementContext[] statements = context.statement();
            foreach (GrammarParser.StatementContext statement in statements)
            {
                statementBlock.AddStatement(Visit(statement));
            }
            return statementBlock;
        }

        public override INode VisitCondition([NotNull] GrammarParser.ConditionContext context)
        {
            // TODO
            return new QLExpression();
        }
    }
}
