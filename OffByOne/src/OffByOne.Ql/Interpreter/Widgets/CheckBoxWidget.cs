namespace OffByOne.Ql.Interpreter.Widgets
{
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;

    public class CheckBoxWidget : QuestionWidget
    {
        public CheckBoxWidget(
            BooleanValue value,
            QuestionStatement statement,
            GuiEnvironment guiEnvironment,
            WidgetStyle style)
            : base(value, statement, guiEnvironment)
        {
            this.CreateControls(statement, style);
        }

        protected CheckBox Input { get; private set; }

        public override void OnObserve(AnswerInput value)
        {
            base.OnObserve(value);
            this.Input.IsChecked = ((BooleanValue)this.Value).Value;
        }

        protected virtual void UpdateValue(object target, object eventArgs)
        {
            this.Value = new BooleanValue(this.Input.IsChecked.Value);
        }

        private void CreateControls(QuestionStatement statement, WidgetStyle style)
        {
            var label = new Label { Content = statement.Label };
            this.Input = new CheckBox();
            this.Input.Checked += this.UpdateValue;
            this.Input.Unchecked += this.UpdateValue;

            style.Apply(label);
            style.Apply(this.Input);

            this.Controls.Add(label);
            this.Controls.Add(this.Input);
        }
    }
}
