namespace OffByOne.Ql.Interpreter.Controls.Base
{
    using System;
    using System.Collections.Generic;
    using System.Windows;
    using Windows = System.Windows.Controls;

    public abstract class Control : IObserver<GuiChange>
    {
        private IDisposable unsubscriber;
        private bool isVisible;

        protected Control(GuiEnvironment environment)
        {
            this.Environment = environment;
            this.Controls = new List<Windows.Control>();
            this.Dependencies = new SortedSet<string>();

            this.unsubscriber = this.Environment.Subscribe(this);
            this.isVisible = true;
        }

        public GuiEnvironment Environment { get; private set; }

        public IList<Windows.Control> Controls { get; set; }

        protected ISet<string> Dependencies { get; }

        public abstract void OnCompleted();

        public virtual void OnError(Exception error)
        {
            throw error;
        }

        public abstract void OnNext(GuiChange value);

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

        private void UpdateControlVisibility()
        {
            foreach (var control in this.Controls)
            {
                control.Visibility = this.isVisible ? Visibility.Visible : Visibility.Collapsed;
            }
        }
    }
}
