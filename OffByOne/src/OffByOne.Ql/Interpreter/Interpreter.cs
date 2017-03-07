namespace OffByOne.Ql.Interpreter
{
    using System;
    using System.Windows;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Expressions.Binary;
    using OffByOne.Ql.Ast.Expressions.Unary;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Branch;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Interpreter.Controls;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class Interpreter
        : IStatementVisitor<Control, GuiEnvironment>
    {
        public Control Visit(FormStatement expression, GuiEnvironment context)
        {
            var questions = new ListView();
            foreach (var s in expression.Statements)
            {
                var q = (QuestionControl)s.Accept(this, context);
                context.Controls.Add(q.Identifier, q);
                questions.Items.Add(q.ControlElement);
                context.Register(q);
            }

            var form = new Window { Title = expression.Identifier };
            form.Content = questions;
            return form;
        }

        public Control Visit(QuestionStatement expression, GuiEnvironment context)
        {
            return new StringControl(expression.Identifier, expression.ComputedValue, context);
        }

        public Control Visit(IfStatement expression, GuiEnvironment context)
        {
            throw new NotImplementedException();
        }
    }
}