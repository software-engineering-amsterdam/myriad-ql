namespace OffByOne.Ql.Common
{
    public interface IObserver<T>
    {
        void OnObserve(T observation);
    }
}
