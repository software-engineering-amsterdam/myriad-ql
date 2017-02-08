namespace OffByOne.Ql
{
    using System;
    using System.Collections;
    using Antlr4.Runtime.Misc;
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Expressions;
    using OffByOne.LanguageCore.Ast.Statements;
    using OffByOne.LanguageCore.Ast.Statements.Branch;
    using OffByOne.LanguageCore.Ast.Statements.Questions;
    using OffByOne.Ql.Generated;

    // TODO: Figure out in which project this file should be.
    public class MyQlVisitor : QlBaseVisitor<AstNode>
    {
        public override AstNode VisitForm([NotNull] QlParser.FormContext context)
        {
            string id = context.Identifier().GetText();
            IList statements = this.VisitStatements(context.stat());

            return new FormStatement(id, statements);
        }

        public override AstNode VisitQuestion([NotNull] QlParser.QuestionContext context)
        {
            string id = context.Identifier(0).GetText();
            string question = context.StringLiteral(0).GetText();
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
                default:
                    throw new ArgumentOutOfRangeException(nameof(type), "Invalid question type.");
            }
        }

        public override AstNode VisitIf([NotNull] QlParser.IfContext context)
        {
            ElseStatement elseStat = null;
            if (context.@else() != null)
            {
                elseStat = (ElseStatement)this.Visit(context.@else());
            }

            Expression condition = (Expression)this.Visit(context.booleanExpression());
            IList statements = this.VisitStatements(context.stat());

            return new IfStatement(condition, statements, elseStat);
        }

        public override AstNode VisitElse([NotNull] QlParser.ElseContext context)
        {
            IList statements = new ArrayList();
            if (context.@if() != null)
            {
                statements.Add(this.Visit(context.@if()));
            }
            else
            {
                statements = this.VisitStatements(context.stat());
            }

            return new ElseStatement(statements);
        }

        public override AstNode VisitBooleanExpression([NotNull] QlParser.BooleanExpressionContext context)
        {
            return base.VisitBooleanExpression(context);
        }

        private IList VisitStatements([NotNull] QlParser.StatContext[] stats)
        {
            IList statements = new ArrayList(stats.Length);
            foreach (var stat in stats)
            {
                statements.Add(this.Visit(stat));
            }

            return statements;
        }
    }
}
