namespace OffByOne.Ql.Tests.Common
{
    using System;
    using System.Threading;
    using System.Threading.Tasks;

    // Borrowed from: https://github.com/xunit/xunit/issues/103#issuecomment-62822506
    public class ThreadHelper
    {
        public static Task StartSTATask(Action action)
        {
            var tcs = new TaskCompletionSource<object>();
            var thread = new Thread(() =>
            {
                try
                {
                    action();
                    tcs.SetResult(new object());
                }
                catch (Exception e)
                {
                    tcs.SetException(e);
                }
            });
            thread.SetApartmentState(ApartmentState.STA);
            thread.Start();
            return tcs.Task;
        }
    }
}
