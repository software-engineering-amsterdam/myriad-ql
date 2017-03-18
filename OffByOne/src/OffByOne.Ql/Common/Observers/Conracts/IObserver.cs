namespace OffByOne.Ql.Common.Observers.Conracts
{
    public interface IObserver<in T>
    {
        void OnObserve(T observation);
    }
}
