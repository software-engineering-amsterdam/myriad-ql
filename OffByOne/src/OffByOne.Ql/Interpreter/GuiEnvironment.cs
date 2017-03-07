namespace OffByOne.Ql.Interpreter
{
    using System.Collections.Generic;

    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Interpreter.Controls.Base;
    using OffByOne.Ql.Values.Contracts;
    using OffByOne.Ql.Visitors.Contracts;

    public class GuiEnvironment : IContext, IObservable
    {
        public GuiEnvironment(TypeEnvironment typeEnvironment)
        {
            this.Controls = new Dictionary<string, QuestionControl>();
            this.Evaluations = typeEnvironment;
            this.Observers = new List<IObserver>();
        }

        public IList<IObserver> Observers { get; private set; }

        public IDictionary<string, QuestionControl> Controls { get; private set; }

        public TypeEnvironment Evaluations { get; }

        public void Register(IObserver observer)
        {
            this.Observers.Add(observer);
        }

        public void NotifyObservers(GuiChange change)
        {
            foreach (IObserver observer in this.Observers)
            {
                observer.Notify(change);
            }
        }

        public void UpdateValues(string identifier, IValue value)
        {
            bool environmentChanged = this.Evaluations.AddOrUpdateValue(identifier, value);
            if (environmentChanged)
            {
                GuiChange change = new GuiChange(identifier, value, this);
                this.NotifyObservers(change);
            }
        }
    }
}
