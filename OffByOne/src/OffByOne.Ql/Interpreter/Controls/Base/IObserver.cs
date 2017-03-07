namespace OffByOne.Ql.Interpreter.Controls.Base
{
    using OffByOne.Ql.Visitors.Contracts;

    public interface IObserver
    {
        void Notify(GuiChange change);
    }
}
