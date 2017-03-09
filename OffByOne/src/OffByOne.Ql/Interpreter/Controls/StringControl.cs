namespace OffByOne.Ql.Interpreter.Controls
{
    using System;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values;

    public class StringControl : QuestionControl
    {
        public StringControl(QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(statement, guiEnvironment)
        {
            this.CreateControl();
            this.Value = new StringValue(string.Empty);
        }

        public override void OnNext(GuiChange value)
        {
            base.OnNext(value);
            ((TextBox)this.RootControl.Items[1]).Text = this.Value.ToString();
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
            var label = new Label() { Content = this.Statement.Label.ToString() };
            var input = new TextBox() { MinWidth = 200 };
            input.KeyUp += (target, eventArgs) =>
            {
                this.Value = new StringValue(input.Text);
            };
            this.RootControl.Items.Add(label);
            this.RootControl.Items.Add(input);
        }
    }
}
