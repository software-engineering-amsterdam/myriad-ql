namespace OffByOne.Ql.Evaluator.Controls.Questions
{
    using System.Windows.Controls;
    using OffByOne.Ql.Ast.Statements;

    internal class BooleanControl : QuestionControl
    {
        public BooleanControl(QuestionStatement statement)
            : base(statement)
        {
            this.Control.Items.Add(new CheckBox());
        }
    }
}