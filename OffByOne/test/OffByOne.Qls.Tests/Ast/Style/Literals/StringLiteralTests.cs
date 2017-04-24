namespace OffByOne.Qls.Tests.Ast.Style.Literals
{
    using System;

    using OffByOne.Qls.Ast.Style.Literals;

    using Xunit;

    public class StringLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new StringLiteral(null));
        }
    }
}
