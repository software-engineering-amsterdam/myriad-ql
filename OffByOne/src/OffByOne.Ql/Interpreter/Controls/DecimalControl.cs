namespace OffByOne.Ql.Interpreter.Controls
{
    using System;
    using System.Collections.Generic;
    using System.Text.RegularExpressions;
    using System.Windows.Controls;
    using System.Windows.Input;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values;

    public class DecimalControl : QuestionControl
    {
        private TextBox input;
        private Label label;

        public DecimalControl(QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(statement, guiEnvironment)
        {
            this.CreateControl();
            this.Value = new DecimalValue(0.0);
        }

        public override void OnNext(GuiChange value)
        {
            base.OnNext(value);
            this.input.Text = ((DecimalValue)this.Value).ToString();
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

            this.input.PreviewTextInput += this.FilterText;
            this.Controls.Add(this.label);
            this.Controls.Add(this.input);
        }

        private void FilterText(object target, TextCompositionEventArgs eventArgs)
        {
            var oldText = this.input.Text;
            var newText = oldText + eventArgs.Text;
            var filter = new Regex("^\\d+((\\.|,)\\d*)?$");
            var isValid = filter.IsMatch(newText);
            eventArgs.Handled = !isValid;
            if (isValid)
            {
                this.Value = new DecimalValue(newText);
            }
        }
    }
}
