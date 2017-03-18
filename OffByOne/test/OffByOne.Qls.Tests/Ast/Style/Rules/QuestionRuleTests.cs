namespace OffByOne.Qls.Tests.Ast.Style.Rules
{
    using System;
    using System.Collections.Generic;

    using OffByOne.Qls.Ast.Style.Properties.Base;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Widgets;

    using Xunit;

    public class QuestionRuleTests
    {
        [Theory]
        [InlineData(null)]
        [InlineData("")]
        [InlineData("          ")]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven(string sampleQuestionName)
        {
            Assert.Throws<ArgumentException>(() => new QuestionRule(sampleQuestionName, null, null));
        }
    }
}
