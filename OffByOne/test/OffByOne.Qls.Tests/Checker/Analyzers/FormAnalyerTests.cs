namespace OffByOne.Qls.Tests.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Qls.Checker.Analyzers;

    using Xunit;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class FormAnalyerTests
    {
        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new FormAnalyer(null));
        }

        [Fact]
        public void Analyze_ShouldThrowExceptionIfNullStyleRootNodeIsGiven()
        {
            var typeAnamyzer = new FormAnalyer();
            Assert.Throws<ArgumentNullException>(() => typeAnamyzer.Analyze(null, new Dictionary<string, ValueType>()));
        }
    }
}
