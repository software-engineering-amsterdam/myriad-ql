namespace OffByOne.Ql.Tests.Ast.Literals
{
    using System;

    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Values;

    using Xunit;

    public class DateLiteralTests
    {
        [Fact]
        public void CreatingNewObject_ShouldHaveCorrectValueCorectDataGiven()
        {
            var testValue = DateTime.Now;

            var expected = new DateValue(testValue.Date);
            var actual = new DateLiteral(testValue.ToString("dd-MM-yyyy"));

            Assert.Equal(expected, actual.Value);
        }

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentException>(() => new DateLiteral(null));
            Assert.Throws<FormatException>(() => new DateLiteral("XaX"));
        }
    }
}
