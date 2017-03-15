namespace OffByOne.Ql.Common
{
    public interface IObserver<in T>
    {
        void OnObserve(T observation);
    }
}
