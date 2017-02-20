namespace OffByOne.Ql
{
    using System;
    using System.Linq;

    using Antlr4.Runtime.Misc;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Ast.Statements.Questions;
    using OffByOne.Ql.Generated;

    // TODO: Extract creation of OperatorExpressions to factory method
    public class MyQlVisitor : QlParserBaseVisitor<AstNode>
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

        public override AstNode VisitExpressionIdentifier([NotNull] QlParser.ExpressionIdentifierContext context)
        {
            return new VariableExpression(context.Identifier().GetText());
        }

        public override AstNode VisitExpressionAdd([NotNull] QlParser.ExpressionAddContext context)
        {
            return base.VisitExpressionAdd(context);
        }

        public override AstNode VisitExpressionAnd([NotNull] QlParser.ExpressionAndContext context)
        {
            return base.VisitExpressionAnd(context);
        }

        public override AstNode VisitExpressionDivide([NotNull] QlParser.ExpressionDivideContext context)
        {
            return base.VisitExpressionDivide(context);
        }

        public override AstNode VisitExpressionEqual([NotNull] QlParser.ExpressionEqualContext context)
        {
            return base.VisitExpressionEqual(context);
        }

        public override AstNode VisitExpressionGreaterThan([NotNull] QlParser.ExpressionGreaterThanContext context)
        {
            return base.VisitExpressionGreaterThan(context);
        }

        public override AstNode VisitExpressionGreaterThanOrEqual([NotNull] QlParser.ExpressionGreaterThanOrEqualContext context)
        {
            return base.VisitExpressionGreaterThanOrEqual(context);
        }

        public override AstNode VisitExpressionLesserThan([NotNull] QlParser.ExpressionLesserThanContext context)
        {
            return base.VisitExpressionLesserThan(context);
        }

        public override AstNode VisitExpressionLesserThanOrEqual([NotNull] QlParser.ExpressionLesserThanOrEqualContext context)
        {
            return base.VisitExpressionLesserThanOrEqual(context);
        }

        public override AstNode VisitExpressionLiteral([NotNull] QlParser.ExpressionLiteralContext context)
        {
            return base.VisitExpressionLiteral(context);
        }

        public override AstNode VisitExpressionMultiply([NotNull] QlParser.ExpressionMultiplyContext context)
        {
            return base.VisitExpressionMultiply(context);
        }

        public override AstNode VisitExpressionNegate([NotNull] QlParser.ExpressionNegateContext context)
        {
            return base.VisitExpressionNegate(context);
        }

        public override AstNode VisitExpressionNot([NotNull] QlParser.ExpressionNotContext context)
        {
            return base.VisitExpressionNot(context);
        }

        public override AstNode VisitExpressionNotEqual([NotNull] QlParser.ExpressionNotEqualContext context)
        {
            return base.VisitExpressionNotEqual(context);
        }

        public override AstNode VisitExpressionOr([NotNull] QlParser.ExpressionOrContext context)
        {
            return base.VisitExpressionOr(context);
        }

        public override AstNode VisitExpressionSubtract([NotNull] QlParser.ExpressionSubtractContext context)
        {
            return base.VisitExpressionSubtract(context);
        }
        #endregion

        #region:Literals
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
#endregion
    }
}
