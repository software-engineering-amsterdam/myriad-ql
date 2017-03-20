namespace OffByOne.Ql.Interpreter.Widgets.Base
{
    using System.Collections.Generic;
    using System.Windows;
    using System.Windows.Controls;

    using OffByOne.Ql.Common.Observers.Conracts;

    public abstract class Widget : IObserver<AnswerInput>
    {
        private bool isVisible;

        protected Widget(GuiEnvironment environment)
        {
            this.Environment = environment;
            this.Controls = new List<Control>();
            this.Dependencies = new SortedSet<string>();

            this.Environment.RegisterObserver(this);
            this.isVisible = true;
        }

        public GuiEnvironment Environment { get; }

        public IList<Control> Controls { get; set; }

        protected ISet<string> Dependencies { get; }

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

        private void UpdateControlVisibility()
        {
            foreach (var control in this.Controls)
            {
                control.Visibility = this.isVisible ? Visibility.Visible : Visibility.Collapsed;
            }
        }
    }
}
