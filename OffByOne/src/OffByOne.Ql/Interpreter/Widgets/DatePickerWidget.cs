namespace OffByOne.Ql.Interpreter.Widgets
{
    using System;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Values.Contracts;

    public class DatePickerWidget : QuestionWidget
    {
        private DatePicker input;
        private Label label;

        public DatePickerWidget(IValue value, QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(value, statement, guiEnvironment)
        {
            this.CreateControls(statement);
        }

        private void CreateControls(QuestionStatement statement)
        {
            this.label = new Label { Content = statement.Label };
            this.input = new DatePicker { SelectedDateFormat = DatePickerFormat.Short };
            this.input.SelectedDateChanged += this.UpdateValue;
            this.Controls.Add(this.label);
            this.Controls.Add(this.input);
        }

        private void UpdateValue(object target, object eventArgs)
        {
            this.Value = new DateValue(this.input.SelectedDate.Value);
        }
    }
}
