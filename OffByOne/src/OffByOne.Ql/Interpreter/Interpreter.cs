namespace OffByOne.Ql.Interpreter
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Windows;
    using System.Windows.Media;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Interpreter.Validators;
    using OffByOne.Ql.Interpreter.Widgets;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Visitors.Contracts;

    public class Interpreter
        : IStatementVisitor<Widget, GuiEnvironment>
    {
        public Widget Visit(FormStatement expression, GuiEnvironment environment)
        {
            var controls = new List<Widget>(expression.Statements.Count());
            foreach (var s in expression.Statements)
            {
                var control = s.Accept(this, environment);
                controls.Add(control);
            }

            var form = new FormWidget(expression, environment, controls);
            return form;
        }

        public Widget Visit(QuestionStatement statement, GuiEnvironment environment)
        {
            Widget question;
            var style = new WidgetStyle
            {
                Color = Colors.Black,
                Width = 200,
            };

            switch (statement.Type)
            {
                case BooleanValueType _:
                    question = new CheckBoxWidget(new BooleanValue(false), statement, environment, style);
                    break;
                case DateValueType _:
                    question = new DatePickerWidget(new DateValue(DateTime.Now), statement, environment, style);
                    break;
                case StringValueType _:
                    question = new TextFieldWidget(new StringValue(string.Empty), statement, environment, style);
                    break;
                case DecimalValueType _:
                    question = new ValidatedTextFieldWidget(new DecimalValue(0), new DecimalValidator(), statement, environment, style);
                    break;
                case IntegerValueType _:
                    question = new ValidatedTextFieldWidget(new IntegerValue(0), new IntegerValidator(), statement, environment, style);
                    break;
                case MoneyValueType _:
                    question = new ValidatedTextFieldWidget(new MoneyValue(0), new MoneyValidator(), statement, environment, style);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(statement.Type));
            }

            return question;
        }

        public Widget Visit(IfStatement expression, GuiEnvironment environment)
        {
            var ifControls = expression.Statements
                .Select(x => x.Accept(this, environment))
                .ToList();
            var elseControls = expression.ElseStatements
                .Select(x => x.Accept(this, environment))
                .ToList();

            var control = new VisibilityWidget(expression, environment, ifControls, elseControls);
            return control;
        }
    }
}