namespace OffByOne.Ql.Interpreter.Widgets
{
    using System.Windows.Media;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Validators.Base;
    using OffByOne.Ql.Values.Contracts;

    public class ValidatedTextFieldWidget : TextFieldWidget
    {
        private IValidator<string> validator;

        public ValidatedTextFieldWidget(
            IValue value,
            IValidator<string> validator,
            QuestionStatement statement,
            GuiEnvironment guiEnvironment,
            WidgetStyle style)
            : base(value, statement, guiEnvironment, style)
        {
            this.validator = validator;
        }

        protected override void UpdateValue()
        {
            if (this.validator.IsValid(this.Input.Text))
            {
                this.Input.BorderBrush = new SolidColorBrush(Colors.DarkGreen);
                base.UpdateValue();
            }
            else
            {
                this.Input.BorderBrush = new SolidColorBrush(Colors.DarkRed);
            }
        }
    }
}
