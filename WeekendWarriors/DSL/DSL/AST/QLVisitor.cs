using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime.Misc;

namespace DSL.AST
{
    public class QLVisitor : GrammarBaseVisitor<INode>
    {
        private List<INode> GetStatements(GrammarParser.StatementContext[] context)
        {
            List<INode> statements = new List<INode>();
            foreach (GrammarParser.StatementContext statement in context)
            {
                statements.Add(Visit(statement));
            }

            return statements;
        }

        public override INode VisitForm([NotNull] GrammarParser.FormContext context)
        {            
            string identifier = context.Identifier().GetText();
            List<INode> statements = GetStatements(context.statement());
            QLForm form = new QLForm(identifier, statements);

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
            INode expression = Visit(context.condition);
            List<INode> thenStatements = GetStatements(context.thenBlock.statement());
            List<INode> elseStatements = GetStatements(context.elseBlock.statement());

            return new QLConditional(expression, thenStatements, elseStatements);
        }

        public override INode VisitComputedQuestion([NotNull] GrammarParser.ComputedQuestionContext context)
        {
            INode question = Visit(context.question());
            INode expression = Visit(context.expression());
            return new QLComputedQuestion(question, expression);
        }

        public override INode VisitID([NotNull] GrammarParser.IDContext context)
        {
            return new QLIdentifier(context.Identifier().GetText());
        }

        public override INode VisitBinaryOp([NotNull] GrammarParser.BinaryOpContext context)
        {
            INode lhs = Visit(context.left);
            INode rhs = Visit(context.right);
            string op = context.op.Text;
            return new QLBinaryOperator(lhs, op, rhs);
        }

        public override INode VisitUnaryOp([NotNull] GrammarParser.UnaryOpContext context)
        {
            string op = context.op.Text;
            INode operand = Visit(context.expression());
            return new QLUnaryOperator(operand, op);
        }

        public override INode VisitMoney([NotNull] GrammarParser.MoneyContext context)
        {
            return new QLMoney(decimal.Parse(context.GetText()));
        }

        public override INode VisitBool([NotNull] GrammarParser.BoolContext context)
        {
            System.Diagnostics.Debug.Assert(context.GetText() == "true" || context.GetText() == "false");
            return new QLBoolean(context.GetText() == "true");
        }

        public override INode VisitString([NotNull] GrammarParser.StringContext context)
        {
            return new QLString(context.GetText());
        }

        public override INode VisitNumber([NotNull] GrammarParser.NumberContext context)
        {
            return new QLNumber(int.Parse(context.GetText()));
        }
    }
}
