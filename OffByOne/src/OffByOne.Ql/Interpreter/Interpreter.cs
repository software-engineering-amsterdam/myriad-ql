namespace OffByOne.Ql.Interpreter
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Windows.Media;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Ql.Interpreter.Validators;
    using OffByOne.Ql.Interpreter.Widgets;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;

    public class Interpreter
        : IStatementVisitor<Widget, GuiEnvironment>
    {
        public Widget Visit(FormStatement statement, GuiEnvironment environment)
        {
            var controls = new List<Widget>(statement.Statements.Count());
            foreach (var s in statement.Statements)
            {
                var control = s.Accept(this, environment);
                controls.Add(control);
            }

            var form = new FormWidget(statement, environment, controls);
            return form;
        }

        public Widget Visit(QuestionStatement statement, GuiEnvironment environment)
        {
            var style = new WidgetStyle
            {
                Color = Colors.Black,
                Width = 200,
            };

            var questionWidget = this.CreateWidgetForQuestion(statement, environment, style);
            return questionWidget;
        }

        public Widget Visit(IfStatement statement, GuiEnvironment environment)
        {
            var ifControls = statement.Statements
                .Select(x => x.Accept(this, environment))
                .ToList();
            var elseControls = statement.ElseStatements
                .Select(x => x.Accept(this, environment))
                .ToList();

            var control = new VisibilityWidget(statement, environment, ifControls, elseControls);
            return control;
        }

        public Widget Visit(Statement statement, GuiEnvironment environment)
        {
            return statement.Accept(this, environment);
        }

        private Widget CreateWidgetForQuestion(QuestionStatement statement, GuiEnvironment environment, WidgetStyle style)
        {
            Widget questionWidget;
            switch (statement.Type)
            {
                case BooleanValueType _:
                    questionWidget = new CheckBoxWidget(new BooleanValue(false), statement, environment, style);
                    break;
                case DateValueType _:
                    questionWidget = new DatePickerWidget(new DateValue(DateTime.Now), statement, environment, style);
                    break;
                case StringValueType _:
                    questionWidget = new TextFieldWidget(new StringValue(string.Empty), statement, environment, style);
                    break;
                case DecimalValueType _:
                    questionWidget = new ValidatedTextFieldWidget(new DecimalValue(0), new DecimalValidator(), statement, environment, style);
                    break;
                case IntegerValueType _:
                    questionWidget = new ValidatedTextFieldWidget(new IntegerValue(0), new IntegerValidator(), statement, environment, style);
                    break;
                case MoneyValueType _:
                    questionWidget = new ValidatedTextFieldWidget(new MoneyValue(0), new MoneyValidator(), statement, environment, style);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(statement.Type));
            }

            return questionWidget;
        }
    }
}