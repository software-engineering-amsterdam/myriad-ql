namespace OffByOne.Ql.Interpreter.Widgets
{
    using System;
    using System.Windows.Controls;
    using System.Windows.Media;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Validators.Base;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values.Contracts;

    public class ValidatedTextFieldWidget : QuestionWidget
    {
        private TextBox input;
        private Label label;
        private IValidator<string> validator;

        public ValidatedTextFieldWidget(IValue value, IValidator<string> validator, QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(value, statement, guiEnvironment)
        {
            this.CreateControls(statement);
            this.validator = validator;
        }

        private void CreateControls(QuestionStatement statement)
        {
            this.label = new Label { Content = statement.Label };
            this.input = new TextBox();
            this.input.KeyUp += this.UpdateValue;
            this.Controls.Add(this.label);
            this.Controls.Add(this.input);
        }

        private void UpdateValue(object target, object eventArgs)
        {
            this.input.BorderBrush = new SolidColorBrush(Colors.DarkRed);
            if (this.validator.IsValid(this.input.Text))
            {
                this.input.BorderBrush = new SolidColorBrush(Colors.DarkGreen);
                this.Value = (IValue)Activator.CreateInstance(this.Value.GetType(), new object[] { this.input.Text });
            }
        }
    }
}
