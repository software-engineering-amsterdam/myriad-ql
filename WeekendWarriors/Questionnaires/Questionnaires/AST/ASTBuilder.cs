using System.Collections.Generic;
using Antlr4.Runtime.Misc;
using Questionnaires.AST.Operators;
using System.ComponentModel;
using System.Diagnostics;
using Questionnaires.AST.Literals;

namespace Questionnaires.AST
{
    public class ASTBuilder : QLBaseVisitor<INode>
    {
        public ASTBuilder()
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
            Form form = new Form(identifier, statements);

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
            
            return new Question(identifier, body, parsedType);
        }

        public override INode VisitConditionalBlock([NotNull] QLParser.ConditionalBlockContext context)
        {         
            INode expression = Visit(context.condition);
            List<INode> thenStatements = GetStatements(context.thenBlock.statement());
            List<INode> elseStatements = new List<INode>();
            if(context.elseBlock != null)
                elseStatements = GetStatements(context.elseBlock.statement());

            Debug.Assert(expression is IExpression);

            return new Conditional((dynamic)expression, thenStatements, elseStatements);
        }

        public override INode VisitComputedQuestion([NotNull] QLParser.ComputedQuestionContext context)
        {
            INode question = Visit(context.question());
            INode expression = Visit(context.expression());
            return new ComputedQuestion((dynamic)question, (dynamic)expression);
        }

        public override INode VisitID([NotNull] QLParser.IDContext context)
        {
            return new Identifier(context.Identifier().GetText());
        }

        public override INode VisitBinaryOp([NotNull] QLParser.BinaryOpContext context)
        {
            INode lhs = Visit(context.left);
            INode rhs = Visit(context.right);

            switch (context.op.Type)
            {
                case QLLexer.OP_ADD:
                   return new Addition((dynamic)lhs, (dynamic)rhs);                   
                case QLLexer.OP_SUB: 
                    return new Subtraction((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_MUL: 
                    return new Multiply((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_DIV: 
                    return new Division((dynamic)lhs,(dynamic)rhs);

                case QLLexer.OP_GT: 
                    return new GreaterThan((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_GE: 
                    return new GreaterThanOrEqual((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_LT: 
                    return new LessThan((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_LE: 
                    return new LessThanOrEqual((dynamic)lhs, (dynamic)rhs);

                case QLLexer.OP_EQ: 
                    return new Equal((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_NE: 
                    return new Inequal((dynamic)lhs, (dynamic)rhs);

                case QLLexer.OP_OR: 
                    return new Or((dynamic)lhs, (dynamic)rhs);
                case QLLexer.OP_AND: 
                    return new And((dynamic)lhs, (dynamic)rhs);

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
                    return new Bang((dynamic)operand);
                case QLLexer.OP_ADD:
                    return VisitPositiveOperation((dynamic)operand);
                case QLLexer.OP_SUB:
                    return VisitNegativeOperation((dynamic)operand);
                default:
                    throw new InvalidEnumArgumentException();
            }          
        }

        public INode VisitPositiveOperation(Number number)
        {
            return new Number("+" + number.StringValue);
        }

        public INode VisitPositiveOperation(Money money)
        {
            return new Money("+" + money.StringValue);
        }

        public INode VisitPositiveOperation(IExpression expression)
        {
            return new Positive(expression);
        }

        public INode VisitNegativeOperation(Number number)
        {
            return new Number("-" + number.StringValue);            
        }       

        public INode VisitNegativeOperation(Money money)
        {
            return new Money("-" + money.StringValue);
        }

        public INode VisitNegativeOperation(IExpression expression)
        {
            return new Negative(expression);
        }

        public override INode VisitMoney([NotNull] QLParser.MoneyContext context)
        {
            return new Money(context.GetText());
        }

        public override INode VisitBool([NotNull] QLParser.BoolContext context)
        {
            System.Diagnostics.Debug.Assert(context.GetText() == "true" || context.GetText() == "false");
            return new Boolean(context.GetText() == "true");
        }

        public override INode VisitString([NotNull] QLParser.StringContext context)
        {
            return new String(context.GetText());
        }

        public override INode VisitNumber([NotNull] QLParser.NumberContext context)
        {
            return new Number(context.GetText());
        }

        public override INode VisitParens([NotNull] QLParser.ParensContext context)
        {
            return Visit(context.expression());
        }
    }
}
