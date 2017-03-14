namespace OffByOne.Ql.Interpreter.Widgets
{
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Values.Base;
    using OffByOne.Ql.Values.Contracts;

    public class CheckBoxWidget : QuestionWidget
    {
        public CheckBoxWidget(BooleanValue value, QuestionStatement statement, GuiEnvironment guiEnvironment)
            : base(value, statement, guiEnvironment)
        {
            this.CreateControls(statement);
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

        private void CreateControls(QuestionStatement statement)
        {
            var label = new Label { Content = statement.Label };
            this.Input = new CheckBox();
            this.Input.Checked += this.UpdateValue;
            this.Input.Unchecked += this.UpdateValue;
            this.Controls.Add(label);
            this.Controls.Add(this.Input);
        }
    }
}
