namespace OffByOne.Ql.Interpreter
{
    using System;
    using System.Collections.Generic;
    using System.Windows.Controls;

    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public class GuiEnvironment : IEnvironment, IObservable<GuiChange>
    {
        private readonly List<IObserver<GuiChange>> observers;

        public GuiEnvironment()
            : this(new TypeEnvironment())
        {
        }

        public GuiEnvironment(TypeEnvironment typeEnvironment)
        {
            this.Evaluations = typeEnvironment;
            this.observers = new List<IObserver<GuiChange>>();
            this.RootControl = new ListView();
        }

        public TypeEnvironment Evaluations { get; }

        public ItemsControl RootControl { get; }

        public void UpdateValues(string identifier, IValue value)
        {
            bool environmentChanged = this.Evaluations.AddOrUpdateValue(identifier, value);
            if (environmentChanged)
            {
                var change = new GuiChange(identifier, value, this);
                this.NotifyObservers(change);
            }
        }

        public void NotifyObservers(GuiChange change)
        {
            foreach (var observer in this.observers)
            {
                observer.OnNext(change);
            }
        }

        public IDisposable Subscribe(IObserver<GuiChange> observer)
        {
            if (!this.observers.Contains(observer))
            {
                this.observers.Add(observer);
            }

            return new Unsubscriber(this.observers, observer);
        }
    }
}
