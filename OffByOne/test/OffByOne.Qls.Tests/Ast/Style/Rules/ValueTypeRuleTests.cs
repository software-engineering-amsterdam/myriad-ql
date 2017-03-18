namespace OffByOne.Qls.Tests.Ast.Style.Rules
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Widgets;

    using Xunit;

    public class ValueTypeRuleTests
    {
        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new ValueTypeRule(
                null,
                new CheckBoxWidget(),
                new List<Property>()));

            Assert.Throws<ArgumentNullException>(() => new ValueTypeRule(null, null, null));
        }
    }
}
