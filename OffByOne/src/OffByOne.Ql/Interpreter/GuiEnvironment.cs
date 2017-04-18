namespace OffByOne.Ql.Interpreter
{
    using System.Collections.Generic;
    using System.Windows.Controls;

    using OffByOne.Ql.Common;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public class GuiEnvironment : IEnvironment, IObservable<AnswerInput>
    {
        private readonly List<IObserver<AnswerInput>> observers;

        public GuiEnvironment()
            : this(new TypeEnvironment())
        {
        }

        public GuiEnvironment(TypeEnvironment typeEnvironment)
        {
            this.Evaluations = typeEnvironment;
            this.observers = new List<IObserver<AnswerInput>>();
            this.RootControl = new ListView();
        }

        public TypeEnvironment Evaluations { get; }

        public ItemsControl RootControl { get; }

        public void UpdateValues(string identifier, IValue value)
        {
            bool environmentChanged = this.Evaluations.AddOrUpdateValue(identifier, value);
            if (environmentChanged)
            {
                var change = new AnswerInput(identifier, value, this);
                this.NotifyObservers(change);
            }
        }

        public void NotifyObservers(AnswerInput change)
        {
            foreach (var observer in this.observers)
            {
                observer.OnObserve(change);
            }
        }

        public void RegisterObserver(IObserver<AnswerInput> observer)
        {
            if (!this.observers.Contains(observer))
            {
                this.observers.Add(observer);
            }
        }
    }
}
