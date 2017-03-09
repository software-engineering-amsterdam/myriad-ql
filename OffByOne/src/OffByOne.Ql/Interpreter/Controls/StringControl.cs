namespace OffByOne.Ql.Interpreter.Controls
{
    using System;
    using System.Linq;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values;

    public class StringControl : QuestionControl
    {
        private TextBox input;
        private Label label;

        public StringControl(QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(statement, guiEnvironment)
        {
            this.CreateControl();
            this.Value = new StringValue(string.Empty);
        }

        public override void OnNext(GuiChange value)
        {
            base.OnNext(value);
            this.Controls.OfType<TextBox>().First().Text = this.Value.ToString();
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
            this.input = new TextBox() { MinWidth = 200 };
            this.input.KeyUp += (target, eventArgs) =>
            {
                this.Value = new StringValue(this.input.Text);
            };
            this.Controls.Add(this.label);
            this.Controls.Add(this.input);
        }
    }
}
