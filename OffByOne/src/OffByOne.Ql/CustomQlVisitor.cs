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
        public override AstNode VisitExpressionBracket([NotNull] QlParser.ExpressionBracketContext context)
        {
            return new BracketExpression((Expression)this.Visit(context.expression()));
        }

        public override AstNode VisitExpressionIdentifier([NotNull] QlParser.ExpressionIdentifierContext context)
        {
            return new VariableExpression(context.Identifier().GetText());
        }

        public override AstNode VisitExpressionNegate([NotNull] QlParser.ExpressionNegateContext context)
        {
            var exp = (Expression)this.Visit(context.expression());
            return new NegativeExpression(exp);
        }

        public override AstNode VisitExpressionNot([NotNull] QlParser.ExpressionNotContext context)
        {
            var exp = (Expression)this.Visit(context.expression());
            return new NotExpression(exp);
        }

        public override AstNode VisitExpressionAdd([NotNull] QlParser.ExpressionAddContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new AddExpression(expressions);
        }

        public override AstNode VisitExpressionAnd([NotNull] QlParser.ExpressionAndContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new AndExpression(expressions);
        }

        public override AstNode VisitExpressionDivide([NotNull] QlParser.ExpressionDivideContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new DivideExpression(expressions);
        }

        public override AstNode VisitExpressionEqual([NotNull] QlParser.ExpressionEqualContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new EqualExpression(expressions);
        }

        public override AstNode VisitExpressionGreaterThan([NotNull] QlParser.ExpressionGreaterThanContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new GreaterThanExpression(expressions);
        }

        public override AstNode VisitExpressionGreaterThanOrEqual([NotNull] QlParser.ExpressionGreaterThanOrEqualContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new GreaterThanOrEqualExpression(expressions);
        }

        public override AstNode VisitExpressionLesserThan([NotNull] QlParser.ExpressionLesserThanContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new LessThanExpression(expressions);
        }

        public override AstNode VisitExpressionLesserThanOrEqual([NotNull] QlParser.ExpressionLesserThanOrEqualContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new LessThanExpression(expressions);
        }

        public override AstNode VisitExpressionLiteral([NotNull] QlParser.ExpressionLiteralContext context)
        {
            return this.Visit(context.GetChild(0));
        }

        public override AstNode VisitExpressionMultiply([NotNull] QlParser.ExpressionMultiplyContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new MultiplyExpression(expressions);
        }

        public override AstNode VisitExpressionNotEqual([NotNull] QlParser.ExpressionNotEqualContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new NotEqualExpression(expressions);
        }

        public override AstNode VisitExpressionOr([NotNull] QlParser.ExpressionOrContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new OrExpression(expressions);
        }

        public override AstNode VisitExpressionSubtract([NotNull] QlParser.ExpressionSubtractContext context)
        {
            var expressions = context.expression()
                .Select(x => (Expression)this.Visit(x))
                .ToList();
            return new SubtractExpression(expressions);
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
