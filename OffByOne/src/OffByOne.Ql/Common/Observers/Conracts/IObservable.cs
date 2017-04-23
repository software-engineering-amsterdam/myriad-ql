namespace OffByOne.Ql.Common.Observers.Conracts
{
    public interface IObservable<T>
    {
        void RegisterObserver(IObserver<T> observer);

        void NotifyObservers(T observation);
    }
}
