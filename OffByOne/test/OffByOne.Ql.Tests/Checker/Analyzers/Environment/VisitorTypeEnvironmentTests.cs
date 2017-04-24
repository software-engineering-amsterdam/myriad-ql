namespace OffByOne.Ql.Tests.Checker.Analyzers.Environment
{
    using System;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Checker.Analyzers.Environment;

    using Xunit;

    public class VisitorTypeEnvironmentTests
    {
        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void AddSymbol_ShouldThrowExceptionIfInvalidNameIsGiven(string symbolName)
        {
            var typeEnv = new TypeEnvironment();
            Assert.Throws<ArgumentNullException>(() => typeEnv.AddSymbol(symbolName, new BooleanValueType()));
        }

        [Fact]
        public void AddSymbol_ShouldThrowExceptionIfNullSymbolTypeIsGiven()
        {
            var typeEnv = new TypeEnvironment();
            Assert.Throws<ArgumentNullException>(() => typeEnv.AddSymbol("HelloWorld", null));
        }

        [Fact]
        public void AddSymbol_ShouldAddSymbolIfCorrectDataIsGiven()
        {
            var typeEnv = new TypeEnvironment();

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
            var typeEnv = new TypeEnvironment();
            Assert.Throws<ArgumentNullException>(() => typeEnv.GetTypeOf(symbolName));
        }
    }
}
