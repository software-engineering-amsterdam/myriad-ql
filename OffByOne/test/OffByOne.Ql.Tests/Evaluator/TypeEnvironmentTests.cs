namespace OffByOne.Ql.Tests.Evaluator
{
    using System;

    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values;
    using OffByOne.Ql.Values.Contracts;

    using Xunit;

    public class TypeEnvironmentTests
    {
        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void AddOrUpdateValue_ShouldThrowExceptionIfInvalidKeyIsGiven(string keyName)
        {
            var typeEnv = new TypeEnvironment();
            var dummyValue = new BooleanValue(true);
            var dummyExpression = new StringLiteral("Hello");

            Assert.Throws<ArgumentException>(() => typeEnv.AddOrUpdateValue(keyName, dummyValue));

            Assert.Throws<ArgumentException>(() => typeEnv.AddOrUpdateValue(keyName, dummyExpression));
        }

        [Fact]
        public void AddOrUpdateValue_ShouldThrowExceptionIfInvalidValueIsGiven()
        {
            var typeEnv = new TypeEnvironment();
            var dummyKey = "HelloWorld!";
            IValue invalidValue = null;

            Assert.Throws<ArgumentNullException>(() => typeEnv.AddOrUpdateValue(dummyKey, invalidValue));
        }

        [Fact]
        public void AddOrUpdateValue_ShouldThrowExceptionIfInvalidExpressionIsGiven()
        {
            var typeEnv = new TypeEnvironment();
            var dummyKey = "HelloWorld!";
            Expression expression = null;

            Assert.Throws<ArgumentNullException>(() => typeEnv.AddOrUpdateValue(dummyKey, expression));
        }

        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void GetOrDefault_ShouldThrowExceptionIfInvalidKeyIsGiven(string keyName)
        {
            var typeEnv = new TypeEnvironment();

            Assert.Throws<ArgumentException>(() => typeEnv.GetValueOf(keyName));
        }
    }
}
