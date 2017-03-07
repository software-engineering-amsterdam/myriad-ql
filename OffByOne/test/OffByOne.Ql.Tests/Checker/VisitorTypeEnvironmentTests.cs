namespace OffByOne.Ql.Tests.Checker
{
    using System;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Checker;

    using Xunit;

    public class VisitorTypeEnvironmentTests
    {
        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void AddSymbol_ShouldThrowExceptionIfInvalidNameIsGiven(string symbolName)
        {
            var typeEnv = new VisitorTypeEnvironment();
            Assert.Throws<ArgumentNullException>(() => typeEnv.AddSymbol(symbolName, new BooleanValueType()));
        }

        [Fact]
        public void AddSymbol_ShouldThrowExceptionIfNullSymbolTypeIsGiven()
        {
            var typeEnv = new VisitorTypeEnvironment();
            Assert.Throws<ArgumentNullException>(() => typeEnv.AddSymbol("HelloWorld", null));
        }

        [Fact]
        public void AddSymbol_ShouldAddSymbolIfCorrectDataIsGiven()
        {
            var typeEnv = new VisitorTypeEnvironment();

            var symbolToAdd = (Name: "HelloWorld", Type: new BooleanValueType());
            typeEnv.AddSymbol(symbolToAdd.Name, symbolToAdd.Type);

            Assert.Equal(symbolToAdd.Type, typeEnv.GetTypeOf(symbolToAdd.Name));
        }

        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void GetTypeOf_ShouldThrowExceptionIfInvalidNameIsGiven(string symbolName)
        {
            var typeEnv = new VisitorTypeEnvironment();
            Assert.Throws<ArgumentNullException>(() => typeEnv.GetTypeOf(symbolName));
        }
    }
}
