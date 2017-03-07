namespace OffByOne.Ql.Interpreter.Controls.Base
{
    public interface IObservable
    {
        void Register(IObserver observer);

        void NotifyObservers(GuiChange change);
    }
}
