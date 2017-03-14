namespace OffByOne.Ql.Interpreter.Widgets
{
    using System;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Values.Contracts;

    public class TextFieldWidget : QuestionWidget
    {
        public TextFieldWidget(IValue value, QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(value, statement, guiEnvironment)
        {
            this.CreateControls(statement);
        }

        protected TextBox Input { get; private set; }

        public override void OnObserve(AnswerInput value)
        {
            base.OnObserve(value);
            this.Input.Text = this.Value.ToString();
        }

        protected void CreateControls(QuestionStatement statement)
        {
            var label = new Label { Content = statement.Label };
            this.Input = new TextBox();
            this.Input.KeyUp += this.UpdateValue;
            this.Controls.Add(label);
            this.Controls.Add(this.Input);
        }

        protected virtual void UpdateValue(object target, object eventArgs)
        {
            IValue value;
            switch (this.Value)
            {
                case StringValue _:
                    value = new StringValue(this.Input.Text);
                    break;
                case DecimalValue _:
                    value = new DecimalValue(this.Input.Text);
                    break;
                case IntegerValue _:
                    value = new IntegerValue(this.Input.Text);
                    break;
                case MoneyValue _:
                    value = new MoneyValue(this.Input.Text);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(this.Value));
            }

            this.Value = value;
        }
    }
}
