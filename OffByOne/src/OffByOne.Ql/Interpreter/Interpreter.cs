namespace OffByOne.Ql.Interpreter
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Interpreter.Controls;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Visitors.Contracts;

    public class Interpreter
        : IStatementVisitor<Control, GuiEnvironment>
    {
        public Control Visit(FormStatement expression, GuiEnvironment environment)
        {
            var controls = new List<Control>(expression.Statements.Count());
            foreach (var s in expression.Statements)
            {
                var control = (Control)s.Accept(this, environment);
                controls.Add(control);
            }

            var form = new FormControl(expression, environment, controls);
            return form;
        }

        public Control Visit(QuestionStatement expression, GuiEnvironment environment)
        {
            Control question;
            switch (expression.Type)
            {
                case BooleanValueType _:
                    question = new BooleanControl(expression, environment);
                    break;
                case DateValueType _:
                    question = new DateControl(expression, environment);
                    break;
                case StringValueType _:
                    question = new StringControl(expression, environment);
                    break;
                case DecimalValueType _:
                    question = new DecimalControl(expression, environment);
                    break;
                case IntegerValueType _:
                    question = new IntegerControl(expression, context);
                    break;
                case MoneyValueType _:
                    question = new MoneyControl(expression, context);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(expression.Type));
            }

            return question;
        }

        public Control Visit(IfStatement expression, GuiEnvironment environment)
        {
            var ifControls = expression.Statements
                .Select(x => x.Accept(this, environment))
                .ToList();
            var elseControls = expression.ElseStatements
                .Select(x => x.Accept(this, environment))
                .ToList();

            var control = new VisibilityControl(expression, environment, ifControls, elseControls);
            return control;
        }
    }
}