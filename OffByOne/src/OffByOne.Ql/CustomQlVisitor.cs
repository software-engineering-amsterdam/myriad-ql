namespace OffByOne.Ql
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using Antlr4.Runtime;
    using Antlr4.Runtime.Misc;

    using OffByOne.LanguageCore;
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Binary.Base;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Generated;

    public class CustomQlVisitor : QlBaseVisitor<AstNode>
    {
        public AstNode Visit(ParserRuleContext context)
        {
            var node = base.Visit(context);
            node.SourceCode = new SourceCode(context);
            return node;
        }

        public override AstNode VisitForm([NotNull] QlParser.FormContext context)
        {
            string id = context.Identifier().GetText();
            var statements = context.stat()
                .Select(x => (Statement)this.Visit(x))
                .ToList();

            return new FormStatement(id, statements);
        }

        public override AstNode VisitQuestion([NotNull] QlParser.QuestionContext context)
        {
            var id = context.Identifier().GetText();
            var question = this.Visit(context.literal()) as LiteralExpression;
            var type = context.Type().GetText();

            var computedValue = context.expression();
            Expression value = null;
            if (computedValue != null)
            {
                value = this.Visit(context.expression()) as Expression;
            }

            switch (type)
            {
                case "boolean":
                    return new QuestionStatement(id, TypeConstants.BooleanType, question, value);
                case "integer":
                    return new QuestionStatement(id, TypeConstants.IntegerType, question, value);
                case "decimal":
                    return new QuestionStatement(id, TypeConstants.DecimalType, question, value);
                case "money":
                    return new QuestionStatement(id, TypeConstants.MoneyType, question, value);
                case "string":
                    return new QuestionStatement(id, TypeConstants.StringType, question, value);
                case "date":
                    return new QuestionStatement(id, TypeConstants.DateType, question, value);
                default:
                    throw new ArgumentOutOfRangeException(nameof(type), "Invalid question type.");
            }
        }

        public override AstNode VisitIfStat([NotNull] QlParser.IfStatContext context)
        {
            var condition = (Expression)this.Visit(context.expression());
            var stats = context.stat()
                    .Select(x => (Statement)this.Visit(x))
                    .ToList();

            var elseContext = context.elseStat();
            var elseStats = new List<Statement>();
            if (elseContext != null)
            {
                elseStats = elseContext.stat()
                    .Select(x => (Statement)this.Visit(x))
                    .ToList();
            }

            return new IfStatement(condition, stats, elseStats);
        }

        public IEnumerable<Statement> GetElseStat([NotNull] QlParser.ElseStatContext context)
        {
            return context.stat()
                    .Select(x => (Statement)this.Visit(x))
                    .ToList();
        }

        #region:Expressions
        public override AstNode VisitBrackets([NotNull] QlParser.BracketsContext context)
        {
            return new BracketExpression((Expression)this.Visit(context.expression()));
        }

        public override AstNode VisitIdentifier([NotNull] QlParser.IdentifierContext context)
        {
            return new VariableExpression(context.Identifier().GetText());
        }

        public override AstNode VisitUnary([NotNull] QlParser.UnaryContext context)
        {
            var exp = (Expression)this.Visit(context.expression());
            return new NegativeExpression(exp);
        }

        public override AstNode VisitAddition([NotNull] QlParser.AdditionContext context)
        {
            var op = context.op.ToString();
            BinaryExpression binExp = null;
            var lhs = this.Visit(context.lhs) as Expression;
            var rhs = this.Visit(context.rhs) as Expression;
            switch (op)
            {
                case "+":
                    binExp = new AddExpression(lhs, rhs);
                    break;
                case "-":
                    binExp = new SubtractExpression(lhs, rhs);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(op), "Unsupported operator.");
            }

            return binExp;
        }

        public override AstNode VisitMultiplication([NotNull] QlParser.MultiplicationContext context)
        {
            var op = context.op.ToString();
            BinaryExpression binExp = null;
            var lhs = this.Visit(context.lhs) as Expression;
            var rhs = this.Visit(context.rhs) as Expression;
            switch (op)
            {
                case "*":
                    binExp = new MultiplyExpression(lhs, rhs);
                    break;
                case "/":
                    binExp = new DivideExpression(lhs, rhs);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(op), "Unsupported operator.");
            }

            return binExp;
        }

        public override AstNode VisitCompare([NotNull] QlParser.CompareContext context)
        {
            var op = context.op.ToString();
            BinaryExpression binExp = null;
            var lhs = this.Visit(context.lhs) as Expression;
            var rhs = this.Visit(context.rhs) as Expression;
            switch (op)
            {
                case "<":
                    binExp = new LessThanExpression(lhs, rhs);
                    break;
                case "<=":
                    binExp = new LessThanOrEqualExpression(lhs, rhs);
                    break;
                case ">":
                    binExp = new GreaterThanExpression(lhs, rhs);
                    break;
                case ">=":
                    binExp = new GreaterThanOrEqualExpression(lhs, rhs);
                    break;
                case "==":
                    binExp = new EqualExpression(lhs, rhs);
                    break;
                case "!=":
                    binExp = new NotEqualExpression(lhs, rhs);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(op), "Unsupported operator.");
            }

            return binExp;
        }

        public override AstNode VisitAnd([NotNull] QlParser.AndContext context)
        {
            var lhs = this.Visit(context.lhs) as Expression;
            var rhs = this.Visit(context.rhs) as Expression;
            return new AndExpression(lhs, rhs);
        }

        public override AstNode VisitOr([NotNull] QlParser.OrContext context)
        {
            var lhs = this.Visit(context.lhs) as Expression;
            var rhs = this.Visit(context.rhs) as Expression;
            return new OrExpression(lhs, rhs);
        }
        #endregion

        #region:Literals
        public override AstNode VisitDateLiteral([NotNull] QlParser.DateLiteralContext context)
        {
            var literal = new DateLiteral(context.DateLiteral().GetText());
            return new LiteralExpression(literal);
        }

        public override AstNode VisitBooleanLiteral([NotNull] QlParser.BooleanLiteralContext context)
        {
            var literal = new BooleanLiteral(context.GetText());
            return new LiteralExpression(literal);
        }

        public override AstNode VisitDecimalLiteral([NotNull] QlParser.DecimalLiteralContext context)
        {
            var literal = new DecimalLiteral(context.GetText());
            return new LiteralExpression(literal);
        }

        public override AstNode VisitIntegerLiteral([NotNull] QlParser.IntegerLiteralContext context)
        {
            var literal = new IntegerLiteral(context.GetText());
            return new LiteralExpression(literal);
        }

        public override AstNode VisitMoneyLiteral([NotNull] QlParser.MoneyLiteralContext context)
        {
            var literal = new MoneyLiteral(context.GetText());
            return new LiteralExpression(literal);
        }

        public override AstNode VisitStringLiteral([NotNull] QlParser.StringLiteralContext context)
        {
            var literal = new StringLiteral(context.GetText());
            return new LiteralExpression(literal);
        }
        #endregion
    }
}
