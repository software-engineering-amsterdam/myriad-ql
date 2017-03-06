namespace OffByOne.Ql.Graphics.Controls.Questions
{
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;

    public abstract class QuestionControl
    {
        protected QuestionControl(QuestionStatement statement)
        {
            this.Control = new ListView();
            var label = new Label
            {
                Content = statement.Label
            };

            this.Control.Items.Add(label);
        }

        public ListView Control { get; private set; }
    }
}
