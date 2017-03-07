namespace OffByOne.Ql.Evaluator.Controls.Questions
{
    using System.Windows.Controls;
    using OffByOne.Ql.Ast.Statements;

    internal class DateControl : QuestionControl
    {
        public DateControl(QuestionStatement statement)
            : base(statement)
        {
            var datePicker = new DatePicker();
            this.Control.Items.Add(datePicker);
        }
    }
}