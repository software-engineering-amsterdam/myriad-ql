namespace OffByOne.Ql.Tests.Checker
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Messages.Base;

    using Xunit;

    public class CheckerReportTests
    {
        [Fact]
        public void Add_ShouldThrowAnExceptionIfNullIsGiven()
        {
            var checkerReport = new CheckerReport();
            CheckerMessage nullMessage = null;
            IList<CheckerMessage> nullMessageCollection = null;

            Assert.Throws<ArgumentNullException>(() => checkerReport.Add(nullMessageCollection));

            Assert.Throws<ArgumentNullException>(() => checkerReport.Add(nullMessage));
        }
    }
}
