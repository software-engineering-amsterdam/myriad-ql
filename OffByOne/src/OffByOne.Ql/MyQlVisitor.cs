namespace OffByOne.Ql
{
    using System;
    using System.Linq;
    using Antlr4.Runtime.Misc;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Ast.Statements.Questions;
    using OffByOne.Ql.Generated;

    // TODO: Extract creation of OperatorExpressions to factory method
    public class MyQlVisitor : QlBaseVisitor<AstNode>
    {
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
            var question = context.StringLiteral().GetText();
            var type = context.Type().GetText();
            switch (type)
            {
                case "boolean":
                    return new BooleanQuestionStatement(id, question);
                case "integer":
                    return new IntegerQuestionStatement(id, question);
                case "decimal":
                    return new DecimalQuestionStatement(id, question);
                case "money":
                    return new MoneyQuestionStatement(id, question);
                case "string":
                    return new StringQuestionStatement(id, question);
                case "date":
                    return new DateQuestionStatement(id, question);
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
            var elseCtx = context.elseStat();
            var elseStat = elseCtx == null ? null : this.Visit(elseCtx);
            return new IfStatement(condition, stats, elseStat as ElseStatement);
        }

        public override AstNode VisitElseStat([NotNull] QlParser.ElseStatContext context)
        {
            var stats = context.stat()
                    .Select(x => (Statement)this.Visit(x))
                    .ToList();
            return new ElseStatement(stats);
        }

        #region:expression
        public override AstNode VisitExpressionBracket([NotNull] QlParser.ExpressionBracketContext context)
        {
            return new BracketExpression((Expression)this.Visit(context.expression()));
        }

        public override AstNode VisitExpressionBinary([NotNull] QlParser.ExpressionBinaryContext context)
        {
            var exps = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            var op = context.BinaryOperator().GetText();
            switch (op)
            {
                case "and":
                    return new AndExpression(exps[0], exps[1]);
                case "or":
                    return new OrExpression(exps[0], exps[1]);
                case "+":
                    return new AddExpression(exps[0], exps[1]);
                case "-":
                    return new SubtractExpression(exps[0], exps[1]);
                case "*":
                    return new MultiplyExpression(exps[0], exps[1]);
                case "/":
                    return new DivideExpression(exps[0], exps[1]);
                case "==":
                    return new EqualExpression(exps[0], exps[1]);
                case ">=":
                    return new GreaterThanOrEqualExpression(exps[0], exps[1]);
                case ">":
                    return new GreaterThanExpression(exps[0], exps[1]);
                case "<=":
                    return new LessThanOrEqualExpression(exps[0], exps[1]);
                case "<":
                    return new LessThanExpression(exps[0], exps[1]);
                default:
                    throw new ArgumentOutOfRangeException(nameof(op), "Unsupported operator for binary expression.");
            }
        }

        public override AstNode VisitExpressionIdentifier([NotNull] QlParser.ExpressionIdentifierContext context)
        {
            return new VariableExpression(context.Identifier().GetText());
        }

        public override AstNode VisitExpressionUnary([NotNull] QlParser.ExpressionUnaryContext context)
        {
            var exp = (Expression)this.Visit(context.expression());
            var op = context.UnaryOperator().GetText();
            switch (op)
            {
                case "not":
                    return new NotExpression(exp);
                default:
                    throw new ArgumentOutOfRangeException(nameof(op), "Unsupported operator for unary expression.");
            }
        }
        #endregion

        public override AstNode VisitDateLiteral([NotNull] QlParser.DateLiteralContext context)
        {
            return new DateLiteral(context.DateLiteral().GetText());
        }

        public override AstNode VisitBooleanLiteral([NotNull] QlParser.BooleanLiteralContext context)
        {
            return base.VisitBooleanLiteral(context);
        }

        public override AstNode VisitDecimalLiteral([NotNull] QlParser.DecimalLiteralContext context)
        {
            return new DecimalLiteral(context.GetText());
        }

        public override AstNode VisitIntegerLiteral([NotNull] QlParser.IntegerLiteralContext context)
        {
            return new IntegerLiteral(context.GetText());
        }

        public override AstNode VisitMoneyLiteral([NotNull] QlParser.MoneyLiteralContext context)
        {
            return new MoneyLiteral(context.GetText());
        }

        public override AstNode VisitStringLiteral([NotNull] QlParser.StringLiteralContext context)
        {
            return new StringLiteral(context.GetText());
        }
    }
}
