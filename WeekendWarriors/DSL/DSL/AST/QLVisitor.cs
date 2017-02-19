using System.Collections.Generic;
using Antlr4.Runtime.Misc;
using DSL.AST.Operators;
using System.ComponentModel;

namespace DSL.AST
{
    public class QLVisitor : QLBaseVisitor<INode>
    {
        private List<INode> GetStatements(QLParser.StatementContext[] context)
        {
            List<INode> statements = new List<INode>();
            foreach (QLParser.StatementContext statement in context)
            {
                statements.Add(Visit(statement));
            }

            return statements;
        }

        public override INode VisitForm([NotNull] QLParser.FormContext context)
        {            
            string identifier = context.Identifier().GetText();
            List<INode> statements = GetStatements(context.statement());
            QLForm form = new QLForm(identifier, statements);

            return form;
        }

        public override INode VisitQuestion([NotNull] QLParser.QuestionContext context)
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

        public override INode VisitConditionalBlock([NotNull] QLParser.ConditionalBlockContext context)
        {         
            INode expression = Visit(context.condition);
            List<INode> thenStatements = GetStatements(context.thenBlock.statement());
            List<INode> elseStatements = new List<INode>();
            if(context.elseBlock != null)
                GetStatements(context.elseBlock.statement());

            return new QLConditional(expression, thenStatements, elseStatements);
        }

        public override INode VisitComputedQuestion([NotNull] QLParser.ComputedQuestionContext context)
        {
            INode question = Visit(context.question());
            INode expression = Visit(context.expression());
            return new QLComputedQuestion(question, expression);
        }

        public override INode VisitID([NotNull] QLParser.IDContext context)
        {
            return new QLIdentifier(context.Identifier().GetText());
        }

        public override INode VisitBinaryOp([NotNull] QLParser.BinaryOpContext context)
        {
            INode lhs = Visit(context.left);
            INode rhs = Visit(context.right);

            switch (context.op.Type)
            {
                case QLLexer.OP_ADD:
                   return new QLArithmeticOperation(lhs, QLBinaryOperator.Addition, rhs);                   
                case QLLexer.OP_SUB: 
                    return new QLArithmeticOperation(lhs, QLBinaryOperator.Subtraction, rhs);
                case QLLexer.OP_MUL: 
                    return new QLArithmeticOperation(lhs, QLBinaryOperator.Multiplication, rhs);
                case QLLexer.OP_DIV: 
                    return new QLArithmeticOperation(lhs, QLBinaryOperator.Division, rhs);

                case QLLexer.OP_GT: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.GreaterThan, rhs);
                case QLLexer.OP_GE: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.GreaterThanOrEqual, rhs);
                case QLLexer.OP_LT: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.LessThan, rhs);
                case QLLexer.OP_LE: 
                    return new QLComparisonOperation(lhs, QLBinaryOperator.LessThanOrEqual, rhs);

                case QLLexer.OP_EQ: 
                    return new QLEqualityOperation(lhs, QLBinaryOperator.Equal, rhs);
                case QLLexer.OP_NE: 
                    return new QLEqualityOperation(lhs, QLBinaryOperator.Inequal, rhs);

                case QLLexer.OP_OR: 
                    return new QLLogicalOperation(lhs, QLBinaryOperator.Or, rhs);
                case QLLexer.OP_AND: 
                    return new QLLogicalOperation(lhs, QLBinaryOperator.And, rhs);

                default:
                    throw new InvalidEnumArgumentException();
            }

            throw new InvalidEnumArgumentException();
        }

        public override INode VisitUnaryOp([NotNull] QLParser.UnaryOpContext context)
        {
            INode operand = Visit(context.expression());

            switch (context.op.Type)
            {
                case QLLexer.OP_BANG:
                    return new QLUnaryOperation(operand, QLUnaryOperator.Bang);
                case QLLexer.OP_ADD:
                    return new QLUnaryOperation(operand, QLUnaryOperator.Plus);
                case QLLexer.OP_SUB:
                    return new QLUnaryOperation(operand, QLUnaryOperator.Minus);
                default:
                    throw new InvalidEnumArgumentException();
            }          
        }

        public override INode VisitMoney([NotNull] QLParser.MoneyContext context)
        {
            return new QLMoney(decimal.Parse(context.GetText()));
        }

        public override INode VisitBool([NotNull] QLParser.BoolContext context)
        {
            System.Diagnostics.Debug.Assert(context.GetText() == "true" || context.GetText() == "false");
            return new QLBoolean(context.GetText() == "true");
        }

        public override INode VisitString([NotNull] QLParser.StringContext context)
        {
            return new QLString(context.GetText());
        }

        public override INode VisitNumber([NotNull] QLParser.NumberContext context)
        {
            return new QLNumber(int.Parse(context.GetText()));
        }

        public override INode VisitParens([NotNull] QLParser.ParensContext context)
        {
            return Visit(context.expression());
        }
    }
}
