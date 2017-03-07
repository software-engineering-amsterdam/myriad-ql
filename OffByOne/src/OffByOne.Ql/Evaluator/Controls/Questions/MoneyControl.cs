namespace OffByOne.Ql.Evaluator.Controls.Questions
{
    using System.Windows.Controls;
    using OffByOne.Ql.Ast.Statements;

    internal class MoneyControl : QuestionControl
    {
        public MoneyControl(QuestionStatement statement)
            : base(statement)
        {
            var input = new TextBox();
            this.Control.Items.Add(input);
        }
    }
}