namespace OffByOne.Qls.Tests.Ast.Style.Widgets
{
    using System;

    using OffByOne.Qls.Ast.Style.Widgets;

    using Xunit;

    public class DropDownListWidgetTests
    {
        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorrectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new DropDownWidget(null));
        }
    }
}
