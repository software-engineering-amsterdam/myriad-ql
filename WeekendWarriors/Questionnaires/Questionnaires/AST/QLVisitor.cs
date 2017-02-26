using System.Collections.Generic;
using Antlr4.Runtime.Misc;
using Questionnaires.AST.Operators;
using System.ComponentModel;
using System.Diagnostics;

namespace Questionnaires.AST
{
    public class QLVisitor : QLBaseVisitor<INode>
    {
        public QLVisitor()
        {
        }

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
            QLType parsedType;

            switch (type)
            {
                case "boolean":
                    parsedType = QLType.Bool;
                    break;                   
                case "money":
                    parsedType = QLType.Money;
                    break;
                case "int":
                    parsedType = QLType.Number;
                    break;
                case "string":
                    parsedType = QLType.String;
                    break;
                default:
                    throw new InvalidEnumArgumentException();
            }
            
            return new QLQuestion(identifier, body, parsedType);
        }

        public override INode VisitConditionalBlock([NotNull] QLParser.ConditionalBlockContext context)
        {         
            INode expression = Visit(context.condition);
            List<INode> thenStatements = GetStatements(context.thenBlock.statement());
            List<INode> elseStatements = new List<INode>();
            if(context.elseBlock != null)
                elseStatements = GetStatements(context.elseBlock.statement());

            Debug.Assert(expression is IQLExpression);

            return new QLConditional((dynamic)expression, thenStatements, elseStatements);
        }

        public override INode VisitComputedQuestion([NotNull] QLParser.ComputedQuestionContext context)
        {
            INode question = Visit(context.question());
            INode expression = Visit(context.expression());
            return new QLComputedQuestion((dynamic)question, (dynamic)expression);
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
                   return new QLAdditionOperation((dynamic)lhs, (dynamic)rhs);                   
                case QLLexer.OP_SUB: 
                    return new QLSubtractionOperation((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_MUL: 
                    return new QLMultiplyOperation((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_DIV: 
                    return new QLDivisionOperation((dynamic)lhs,(dynamic)rhs);

                case QLLexer.OP_GT: 
                    return new QLGreaterThanOperation((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_GE: 
                    return new QLGreaterThanOrEqualOperation((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_LT: 
                    return new QLLessThanOperation((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_LE: 
                    return new QLLessThanOrEqualOperation((dynamic)lhs, (dynamic)rhs);

                case QLLexer.OP_EQ: 
                    return new QLEqualOperation((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_NE: 
                    return new QLInequalOperation((dynamic)lhs, (dynamic)rhs);

                case QLLexer.OP_OR: 
                    return new QLOrOperation((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_AND: 
                    return new QLAndOperation((dynamic)lhs, (dynamic)rhs);

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
                    return new QLBangOperation((dynamic)operand);
                case QLLexer.OP_ADD:
                    return VisitPositiveOperation((dynamic)operand);
                case QLLexer.OP_SUB:
                    return VisitNegativeOperation((dynamic)operand);
                default:
                    throw new InvalidEnumArgumentException();
            }          
        }

        public INode VisitPositiveOperation(QLNumber number)
        {
            return new QLNumber("+" + number.StringValue);
        }

        public INode VisitPositiveOperation(QLMoney money)
        {
            return new QLMoney("+" + money.StringValue);
        }

        public INode VisitPositiveOperation(IQLExpression expression)
        {
            return new QLPositiveOperation(expression);
        }

        public INode VisitNegativeOperation(QLNumber number)
        {
            return new QLNumber("-" + number.StringValue);            
        }       

        public INode VisitNegativeOperation(QLMoney money)
        {
            return new QLMoney("-" + money.StringValue);
        }

        public INode VisitNegativeOperation(IQLExpression expression)
        {
            return new QLNegativeOperation(expression);
        }

        public override INode VisitMoney([NotNull] QLParser.MoneyContext context)
        {
            return new QLMoney(context.GetText());
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
            return new QLNumber(context.GetText());
        }

        public override INode VisitParens([NotNull] QLParser.ParensContext context)
        {
            return Visit(context.expression());
        }
    }
}
