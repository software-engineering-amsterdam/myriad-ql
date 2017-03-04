namespace OffByOne.Ql.Graphics.Controls.Questions
{
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;

    internal class FloatControl : QuestionControl
    {
        public FloatControl(QuestionStatement statement)
            : base(statement)
        {
            var input = new TextBox();
            this.Control.Items.Add(input);
        }
    }
}