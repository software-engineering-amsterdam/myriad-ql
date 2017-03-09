namespace OffByOne.Ql.Interpreter.Controls
{
    using System;
    using System.Linq;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values;

    public class DateControl : QuestionControl
    {
        private DatePicker input;
        private Label label;

        public DateControl(QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(statement, guiEnvironment)
        {
            this.CreateControl();
            this.Value = new DateValue(DateTime.Now);
        }

        public override void OnNext(GuiChange value)
        {
            base.OnNext(value);
            this.input.Text = ((DateValue)this.Value).Value.ToShortDateString();
        }

        public override void OnCompleted()
        {
            throw new NotImplementedException();
        }

        public override void OnError(Exception error)
        {
            throw error;
        }

        private void CreateControl()
        {
            this.label = new Label() { Content = this.Statement.Label };
            this.input = new DatePicker();
            this.input.SelectedDateFormat = DatePickerFormat.Short;
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
