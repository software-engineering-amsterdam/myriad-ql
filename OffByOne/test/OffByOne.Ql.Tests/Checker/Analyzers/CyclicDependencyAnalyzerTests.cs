namespace OffByOne.Ql.Tests.Checker.Analyzers
{
    using System;

    using OffByOne.Ql.Checker.Analyzers;

    using Xunit;

    public class CyclicDependencyAnalyzerTests
    {
        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new CyclicDependencyAnalyzer(null));
        }
    }
}
