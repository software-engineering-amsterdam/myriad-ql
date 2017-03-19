using Antlr4.Runtime.Misc;
using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST.Literals;
using Questionnaires.QL.AST.Operators;
using Questionnaires.QL.AST.Types;
using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace Questionnaires.QL.AST
{
    public class ASTBuilder : QLBaseVisitor<INode>
    {
        private Result Messages;
        private CSTBuilder CSTBuilder;

        public ASTBuilder(Result result)
        {
            Messages = result;
            CSTBuilder = new CSTBuilder(Messages);
        }

        public Form BuildForm(string input)
        {
            var CST = CSTBuilder.BuildForm(input);
            if (Messages.ContainsErrors())
                throw new ParseException();

            return Visit(CST) as Form;
        }

        public IExpression BuildExpression(string input)
        {
            var CST = CSTBuilder.BuildExpression(input);
            if (Messages.ContainsErrors())
                throw new ParseException();

            return Visit(CST) as IExpression;
        }

        public ComputedQuestion BuildComputedQuestion(string input)
        {
            var CST = CSTBuilder.BuildComputedQuestion(input);
            if (Messages.ContainsErrors())
                throw new ParseException();

            return Visit(CST) as ComputedQuestion;
        }

        private List<IStatement> GetStatements(QLParser.StatementContext[] context)
        {
            if (context == null)
                return new List<IStatement>();

            var statements = new List<IStatement>();
            foreach (QLParser.StatementContext statement in context)
            {
                statements.Add((dynamic)statement.Accept(this));
            }

            return statements;
        }

        public override INode VisitForm([NotNull] QLParser.FormContext context)
        {
            var identifier = context.Identifier().GetText();
            var statements = GetStatements(context.statement());
            return new Form(identifier, statements);
        }

        public override INode VisitQuestion([NotNull] QLParser.QuestionContext context)
        {
            var identifier = context.Identifier().GetText();
            var body = context.StringLiteral().GetText();
            body = Utility.String.TrimQuotes(body);
            IType type = GetTypeFromString(context.Type().GetText());

            return new Question(identifier, body, type);
        }

        public override INode VisitConditionalBlock([NotNull] QLParser.ConditionalBlockContext context)
        {
            var expression = context.condition.Accept(this);
            Debug.Assert(expression is IExpression);

            var thenStatements = GetStatements(context.thenBlock.statement());
            var elseStatements = GetStatements(context.elseBlock?.statement());

            return new Conditional((dynamic)expression, thenStatements, elseStatements);
        }

        public override INode VisitComputedQuestion([NotNull] QLParser.ComputedQuestionContext context)
        {
            var question = context.question().Accept(this);
            var expression = context.expression().Accept(this);
            return new ComputedQuestion((dynamic)question, (dynamic)expression);
        }

        public override INode VisitID([NotNull] QLParser.IDContext context)
        {
            return new Identifier(context.Identifier().GetText());
        }

        public override INode VisitBinaryOp([NotNull] QLParser.BinaryOpContext context)
        {
            var lhs = context.left.Accept(this);
            var rhs = context.right.Accept(this);
            return CreateBinaryOperator(context.op.Text, lhs, rhs);
        }

        private INode CreateBinaryOperator(string op, INode lhs, INode rhs)
        {
            Utility.Assertions.AssertInRange(op, new[] { "+", "-", "*", "/", ">", ">=", "<", "<=", "==", "!=", "||", "&&" });

            switch (op)
            {
                case "+":
                    return new Addition((dynamic)lhs, (dynamic)rhs);
                case "-":
                    return new Subtraction((dynamic)lhs, (dynamic)rhs);
                case "*":
                    return new Multiply((dynamic)lhs, (dynamic)rhs);
                case "/":
                    return new Division((dynamic)lhs, (dynamic)rhs);
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
            }
            throw new ArgumentException();
        }

        public override INode VisitUnaryOp([NotNull] QLParser.UnaryOpContext context)
        {
            var operand = context.expression().Accept(this);
            var op = context.op.Text;
            return CreateUnaryOperator(operand, op);
        }

        private INode CreateUnaryOperator(INode operand, string op)
        {
            Utility.Assertions.AssertInRange(op, new[] { "+", "-", "!" });

            switch (op)
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
            return new Number("+" + number.ValueAsString);
        }

        public INode VisitPositiveOperation(Money money)
        {
            return new Money("+" + money.ValueAsString);
        }

        public INode VisitPositiveOperation(IExpression expression)
        {
            return new Positive(expression);
        }

        public INode VisitNegativeOperation(Number number)
        {
            return new Number("-" + number.ValueAsString);
        }

        public INode VisitNegativeOperation(Money money)
        {
            return new Money("-" + money.ValueAsString);
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
            Utility.Assertions.AssertInRange(context.GetText(), new[] { "true", "false" });

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

        private string RemoveDoubleQuotesAtStartAndEnd(string unmodifiedString)
        {
            var modifiedString = unmodifiedString.Remove(0, 1);
            modifiedString = modifiedString.Remove(modifiedString.Length - 1, 1);
            return modifiedString;
        }

        private IType GetTypeFromString(string type)
        {
            Utility.Assertions.AssertInRange(type, new[] { "boolean", "money", "int", "string" });

            switch (type)
            {
                case "boolean":
                    return new BooleanType();
                case "money":
                    return new MoneyType();
                case "int":
                    return new IntegerType();
                case "string":
                    return new StringType();
            }

            throw new ArgumentException();
        }


    }
}
