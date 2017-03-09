namespace OffByOne.Ql.Interpreter.Controls.Base
{
    using System;
    using System.Collections.Generic;

    internal class Unsubscriber : IDisposable
    {
        private List<IObserver<GuiChange>> observers;
        private IObserver<GuiChange> observer;

        public Unsubscriber(List<IObserver<GuiChange>> observers, IObserver<GuiChange> observer)
        {
            this.observers = observers;
            this.observer = observer;
        }

        public void Dispose()
        {
            if (this.observer != null && this.observers.Contains(this.observer))
            {
                this.observers.Remove(this.observer);
            }
        }
    }
}
