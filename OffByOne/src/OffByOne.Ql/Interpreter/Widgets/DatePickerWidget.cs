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

        protected virtual void UpdateValue()
        {
            this.Value = new DateValue(this.Input.SelectedDate.Value);
        }

        private void CreateControls(QuestionStatement statement, WidgetStyle style)
        {
            this.Input = new DatePicker { SelectedDateFormat = DatePickerFormat.Short };
            this.Input.SelectedDateChanged += (e, a) => this.UpdateValue();
            this.Input.IsEnabled = !this.IsReadOnly();
            this.Input.SelectedDate = ((DateValue)this.Value).Value;

            this.CreateControls(this.Input, statement, style);
        }
    }
}
