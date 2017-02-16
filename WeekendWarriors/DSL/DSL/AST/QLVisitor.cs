using System.Collections.Generic;
using Antlr4.Runtime.Misc;
using DSL.AST.Operators;
using System.ComponentModel;

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
            // TODO: We should be able to do better than this, but I could not make it work.
            switch (type)
            {
                case "boolean":
                    return new QLQuestion(identifier, body, QLType.Bool);
                case "money":
                    return new QLQuestion(identifier, body, QLType.Money);
                case "int":
                    return new QLQuestion(identifier, body, QLType.Number);
                case "string":
                    return new QLQuestion(identifier, body, QLType.String);
            }

            throw new InvalidEnumArgumentException();
        }

        public override INode VisitConditionalBlock([NotNull] GrammarParser.ConditionalBlockContext context)
        {         
            INode expression = Visit(context.condition);
            List<INode> thenStatements = GetStatements(context.thenBlock.statement());
            List<INode> elseStatements = new List<INode>();
            if(context.elseBlock != null)
                GetStatements(context.elseBlock.statement());

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

            switch (context.op.Type)
            {
                case GrammarLexer.OP_ADD:
                   return new QLArithmeticOperation(lhs, QLBinaryOperator.Addition, rhs);                   
                case GrammarLexer.OP_SUB: 
                    return new QLArithmeticOperation(lhs, QLBinaryOperator.Subtraction, rhs);
                case GrammarLexer.OP_MUL: 
                    return new QLArithmeticOperation(lhs, QLBinaryOperator.Multiplication, rhs);
                case GrammarLexer.OP_DIV: 
                    return new QLArithmeticOperation(lhs, QLBinaryOperator.Division, rhs);

                case GrammarLexer.OP_GT: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.GreaterThan, rhs);
                case GrammarLexer.OP_GE: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.GreaterThanOrEqual, rhs);
                case GrammarLexer.OP_LT: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.LessThan, rhs);
                case GrammarLexer.OP_LE: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.LessThanOrEqual, rhs);

                case GrammarLexer.OP_EQ: 
                    return new QLEqualityOperation(lhs, QLBinaryOperator.Equal, rhs);
                case GrammarLexer.OP_NE: 
                    return new QLEqualityOperation(lhs, QLBinaryOperator.Inequal, rhs);

                case GrammarLexer.OP_OR: 
                    return new QLLogicalOperation(lhs, QLBinaryOperator.Or, rhs);
                case GrammarLexer.OP_AND: 
                    return new QLLogicalOperation(lhs, QLBinaryOperator.And, rhs);

                default:
                    throw new InvalidEnumArgumentException();
            }

            throw new InvalidEnumArgumentException();
        }

        public override INode VisitUnaryOp([NotNull] GrammarParser.UnaryOpContext context)
        {
            INode operand = Visit(context.expression());

            switch (context.op.Type)
            {
                case GrammarLexer.OP_BANG:
                    return new QLUnaryOperation(operand, QLUnaryOperator.Bang);
                case GrammarLexer.OP_ADD:
                    return new QLUnaryOperation(operand, QLUnaryOperator.Plus);
                case GrammarLexer.OP_SUB:
                    return new QLUnaryOperation(operand, QLUnaryOperator.Minus);
                default:
                    throw new InvalidEnumArgumentException();
            }          
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

        public override INode VisitParens([NotNull] GrammarParser.ParensContext context)
        {
            return Visit(context.expression());
        }
    }
}
