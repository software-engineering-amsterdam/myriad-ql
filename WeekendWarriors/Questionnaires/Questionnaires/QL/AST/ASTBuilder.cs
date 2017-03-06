using System.Collections.Generic;
using Antlr4.Runtime.Misc;
using Questionnaires.QL.AST.Operators;
using System.ComponentModel;
using System.Diagnostics;
using Questionnaires.QL.AST.Literals;
using Questionnaires.Value;
using System;

namespace Questionnaires.QL.AST
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
                statements.Add(statement.Accept(this));
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
            IValue parsedType;

            switch (type)
            {
                case "boolean":
                    parsedType = new BoolValue();
                    break;                   
                case "money":
                    parsedType = new DecimalValue();
                    break;
                case "int":
                    parsedType = new IntValue();
                    break;
                case "string":
                    parsedType = new StringValue();
                    break;
                default:
                    throw new ArgumentException();
            }
            
            return new Question(identifier, body, parsedType);
        }

        public override INode VisitConditionalBlock([NotNull] QLParser.ConditionalBlockContext context)
        {
            INode expression = context.condition.Accept(this);
            List<INode> thenStatements = GetStatements(context.thenBlock.statement());
            List<INode> elseStatements = new List<INode>();
            if(context.elseBlock != null)
                elseStatements = GetStatements(context.elseBlock.statement());

            Debug.Assert(expression is IExpression);

            return new Conditional((dynamic)expression, thenStatements, elseStatements);
        }

        public override INode VisitComputedQuestion([NotNull] QLParser.ComputedQuestionContext context)
        {
            INode question = context.question().Accept(this);
            INode expression = context.expression().Accept(this);
            return new ComputedQuestion((dynamic)question, (dynamic)expression);
        }

        public override INode VisitID([NotNull] QLParser.IDContext context)
        {
            return new Identifier(context.Identifier().GetText());
        }

        public override INode VisitBinaryOp([NotNull] QLParser.BinaryOpContext context)
        {
            INode lhs = context.left.Accept(this);
            INode rhs = context.right.Accept(this);

            switch (context.op.Text)
            {
                case "+":
                   return new Addition((dynamic)lhs, (dynamic)rhs);
                case "-": 
                    return new Subtraction((dynamic)lhs, (dynamic)rhs);
                case "*":
                    return new Multiply((dynamic)lhs, (dynamic)rhs);
                case "/":
                    return new Division((dynamic)lhs,(dynamic)rhs);
                case ">":
                    return new GreaterThan((dynamic)lhs, (dynamic)rhs);
                case ">=":
                    return new GreaterThanOrEqual((dynamic)lhs, (dynamic)rhs);
                case "<":
                    return new LessThan((dynamic)lhs, (dynamic)rhs);
                case "<=":
                    return new LessThanOrEqual((dynamic)lhs, (dynamic)rhs);
                case "==":
                    return new Equal((dynamic)lhs, (dynamic)rhs);
                case "!=":
                    return new Inequal((dynamic)lhs, (dynamic)rhs);
                case "||":
                    return new Or((dynamic)lhs, (dynamic)rhs);
                case "&&":
                    return new And((dynamic)lhs, (dynamic)rhs);
                default:
                    throw new ArgumentException();
            }
        }

        public override INode VisitUnaryOp([NotNull] QLParser.UnaryOpContext context)
        {
            INode operand = context.expression().Accept(this);

            switch (context.op.Text)
            {
                case "!":
                    return new Bang((dynamic)operand);
                case "+":
                    return VisitPositiveOperation((dynamic)operand);
                case "-":
                    return VisitNegativeOperation((dynamic)operand);
                default:
                    throw new ArgumentException();
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
            return new Literals.Boolean(context.GetText() == "true");
        }

        public override INode VisitString([NotNull] QLParser.StringContext context)
        {
            return new Literals.String(context.GetText());
        }

        public override INode VisitNumber([NotNull] QLParser.NumberContext context)
        {
            return new Number(context.GetText());
        }

        public override INode VisitParens([NotNull] QLParser.ParensContext context)
        {
            return context.expression().Accept(this);
        }
    }
}
