namespace OffByOne.Ql
{
    using System;
    using System.Linq;

    using Antlr4.Runtime;
    using Antlr4.Runtime.Misc;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Expressions.Base;
    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Generated;

    // TODO: Extract creation of OperatorExpressions to factory method
    public class CustomQlVisitor : QlParserBaseVisitor<AstNode>
    {
        // TODO: Is this the right place? We probably want to use it in QLS too, right?
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
            var question = this.Visit(context.literal()) as StringLiteral;
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
                    return new QuestionStatement(id, new BooleanValueType(), question, value);
                case "integer":
                    return new QuestionStatement(id, new IntegerValueType(), question, value);
                case "decimal":
                    return new QuestionStatement(id, new FloatValueType(), question, value);
                case "money":
                    return new QuestionStatement(id, new MoneyValueType(), question, value);
                case "string":
                    return new QuestionStatement(id, new StringValueType(), question, value);
                case "date":
                    return new QuestionStatement(id, new DateValueType(), question, value);
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

#region:Expressions
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
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new AddExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionAnd([NotNull] QlParser.ExpressionAndContext context)
        {
            var exp = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new AndExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionDivide([NotNull] QlParser.ExpressionDivideContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new DivideExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionEqual([NotNull] QlParser.ExpressionEqualContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new EqualExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionGreaterThan([NotNull] QlParser.ExpressionGreaterThanContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new GreaterThanExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionGreaterThanOrEqual([NotNull] QlParser.ExpressionGreaterThanOrEqualContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new GreaterThanOrEqualExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionLesserThan([NotNull] QlParser.ExpressionLesserThanContext context)
        {
            var exp = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new LessThanExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionLesserThanOrEqual([NotNull] QlParser.ExpressionLesserThanOrEqualContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new LessThanExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionLiteral([NotNull] QlParser.ExpressionLiteralContext context)
        {
            return this.Visit(context.GetChild(0));
        }

        public override AstNode VisitExpressionMultiply([NotNull] QlParser.ExpressionMultiplyContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new MultiplyExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionNegate([NotNull] QlParser.ExpressionNegateContext context)
        {
            var exp = (Expression)context.expression().GetChild(0);
            return new NotExpression(exp);
        }

        public override AstNode VisitExpressionNot([NotNull] QlParser.ExpressionNotContext context)
        {
            var exp = (Expression)context.expression().GetChild(0);
            return new NotExpression(exp);
        }

        public override AstNode VisitExpressionNotEqual([NotNull] QlParser.ExpressionNotEqualContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new NotEqualExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionOr([NotNull] QlParser.ExpressionOrContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new OrExpression(exp[0], exp[1]);
        }

        public override AstNode VisitExpressionSubtract([NotNull] QlParser.ExpressionSubtractContext context)
        {
            var exp = context.expression()
                   .Select(x => (Expression)this.Visit(x))
                   .ToList();
            return new SubtractExpression(exp[0], exp[1]);
        }
        #endregion

#region:Literals
        public override AstNode VisitDateLiteral([NotNull] QlParser.DateLiteralContext context)
        {
            return new DateLiteral(context.DateLiteral().GetText());
        }

        public override AstNode VisitBooleanLiteral([NotNull] QlParser.BooleanLiteralContext context)
        {
            return new BooleanLiteral(context.GetText());
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
