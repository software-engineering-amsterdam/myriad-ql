namespace OffByOne.Ql.Interpreter.Widgets
{
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;

    public class DatePickerWidget : QuestionWidget
    {
        public DatePickerWidget(
            DateValue value,
            QuestionStatement statement,
            GuiEnvironment guiEnvironment,
            WidgetStyle style)
            : base(value, statement, guiEnvironment)
        {
            this.CreateControls(statement, style);
        }

        protected DatePicker Input { get; private set; }

        public override void OnObserve(AnswerInput value)
        {
            base.OnObserve(value);
            this.Input.SelectedDate = ((DateValue)this.Value).Value;
        }

        protected virtual void UpdateValue(object target, object eventArgs)
        {
            this.Value = new DateValue(this.Input.SelectedDate.Value);
        }

        private void CreateControls(QuestionStatement statement, WidgetStyle style)
        {
            var label = new Label { Content = statement.Label };
            this.Input = new DatePicker { SelectedDateFormat = DatePickerFormat.Short };
            this.Input.SelectedDateChanged += this.UpdateValue;
            this.Input.IsEnabled = !this.IsReadOnly();

            style.Apply(label);
            style.Apply(this.Input);

            this.Controls.Add(label);
            this.Controls.Add(this.Input);
        }
    }
}
