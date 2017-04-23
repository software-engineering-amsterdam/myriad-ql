namespace OffByOne.Ql.Interpreter.Widgets.Base
{
    using System.Collections.Generic;
    using System.Windows;
    using System.Windows.Controls;

    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Common.Collections;
    using OffByOne.Ql.Common.Observers.Conracts;

    public abstract class Widget : IObserver<AnswerInput>
    {
        private bool isVisible;

        protected Widget(GuiEnvironment environment)
        {
            this.Environment = environment;
            this.Controls = new OrderedDictionary<string, Control>();
            this.Dependencies = new SortedSet<string>();

            this.Environment.RegisterObserver(this);
            this.isVisible = true;
        }

        public GuiEnvironment Environment { get; }

        public IDictionary<string, Control> Controls { get; set; }

        protected ISet<string> Dependencies { get; }

        public void AddControls(IDictionary<string, Control> controls)
        {
            foreach (var control in controls)
            {
                this.Controls.Add(control.Key, control.Value);
            }
        }

        public void Show()
        {
            this.isVisible = true;
            this.UpdateControlVisibility();
        }

        public void Hide()
        {
            this.isVisible = false;
            this.UpdateControlVisibility();
        }

        public abstract void OnObserve(AnswerInput observation);

        protected void CreateControls(Control input, QuestionStatement statement, WidgetStyle style)
        {
            var label = new Label { Content = statement.Label };

            style.Apply(label);
            style.Apply(input);

            this.Controls.Add(statement.Identifier + "Label", label);
            this.Controls.Add(statement.Identifier, input);
        }

        private void UpdateControlVisibility()
        {
            foreach (var control in this.Controls.Values)
            {
                control.Visibility = this.isVisible ? Visibility.Visible : Visibility.Collapsed;
            }
        }
    }
}
