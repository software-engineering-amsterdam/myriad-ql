namespace OffByOne.Ql.Interpreter
{
    using System.Collections.Generic;
    using System.Linq;
    using System.Windows;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Controls;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class Interpreter
        : IStatementVisitor<Control, GuiEnvironment>
    {
        public Control Visit(FormStatement expression, GuiEnvironment context)
        {
            var controls = new List<Control>(expression.Statements.Count());
            foreach (var s in expression.Statements)
            {
                var control = (Control)s.Accept(this, context);
                controls.Add(control);
            }

            var form = new FormControl(expression, context, controls);
            return form;
        }

        public Control Visit(QuestionStatement expression, GuiEnvironment context)
        {
            var question = new StringControl(expression, context);
            return question;
        }

        public Control Visit(IfStatement expression, GuiEnvironment context)
        {
            var ifControls = expression.Statements
                .Select(x => x.Accept(this, context))
                .ToList();
            var elseControls = expression.ElseStatements
                .Select(x => x.Accept(this, context))
                .ToList();

            var control = new VisibilityControl(expression, context, ifControls, elseControls);
            return control;
        }
    }
}