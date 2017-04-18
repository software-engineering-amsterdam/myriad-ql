namespace OffByOne.Ql.Common
{
    public interface IObservable<T>
    {
        void RegisterObserver(IObserver<T> observer);

        void NotifyObservers(T observation);
    }
}
