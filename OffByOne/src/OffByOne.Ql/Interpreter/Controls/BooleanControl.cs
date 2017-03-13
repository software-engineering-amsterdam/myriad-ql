namespace OffByOne.Ql.Interpreter.Controls
{
    using System;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values;

    public class BooleanControl : QuestionControl
    {
        private CheckBox input;
        private Label label;

        public BooleanControl(QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(statement, guiEnvironment)
        {
            this.CreateControl();
            this.Value = new BooleanValue(false);
        }

        public override void OnNext(GuiChange value)
        {
            base.OnNext(value);
            this.input.IsChecked = ((BooleanValue)this.Value).Value;
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
            this.label = new Label { Content = this.Statement.Label };
            this.input = new CheckBox();
            this.input.Checked += this.UpdateValue;
            this.input.Unchecked += this.UpdateValue;
            this.Controls.Add(this.label);
            this.Controls.Add(this.input);
        }

        private void UpdateValue(object target, object eventArgs)
        {
            this.Value = new BooleanValue(this.input.IsChecked.Value);
        }
    }
}
