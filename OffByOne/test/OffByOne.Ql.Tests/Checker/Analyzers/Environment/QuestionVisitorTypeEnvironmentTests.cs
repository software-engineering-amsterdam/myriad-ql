namespace OffByOne.Ql.Tests.Checker.Analyzers.Environment
{
    using System;

    using OffByOne.Ql.Checker.Analyzers.Environment;

    using Xunit;

    public class QuestionVisitorTypeEnvironmentTests
    {
        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void AddQuestionName_ShouldThrowExceptionIfInvalidNameIsGiven(string questionName)
        {
            var typeEnv = new QuestionEnvironment();
            Assert.Throws<ArgumentException>(() => typeEnv.AddQuestionIdentifier(questionName));
        }

        [Fact]
        public void AddQuestionName_ShouldShouldAddQuestionNameValidNameIsGiven()
        {
            var typeEnv = new QuestionEnvironment();
            var questionName = "HeyWorld!";
            typeEnv.AddQuestionIdentifier(questionName);

            Assert.True(typeEnv.IsIdentifierDuplicate(questionName));
        }

        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void AddQuestionLabel_ShouldThrowExceptionIfInvalidNameIsGiven(string questionLabel)
        {
            var typeEnv = new QuestionEnvironment();
            Assert.Throws<ArgumentException>(() => typeEnv.AddQuestionLabel(questionLabel));
        }

        [Fact]
        public void AddQuestionLabel_ShouldShouldAddQuestionLabelValidNameIsGiven()
        {
            var typeEnv = new QuestionEnvironment();
            var questionLabel = "HeyWorld!";
            typeEnv.AddQuestionLabel(questionLabel);

            Assert.True(typeEnv.IsLableDuplicate(questionLabel));
        }

        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void IsNameDuplicate_ShouldThrowExceptionIfInvalidNameIsGiven(string questionLabel)
        {
            var typeEnv = new QuestionEnvironment();
            Assert.Throws<ArgumentException>(() => typeEnv.IsIdentifierDuplicate(questionLabel));
        }

        [Theory]
        [InlineData("")]
        [InlineData("         ")]
        [InlineData(null)]
        public void IsLableDuplicate_ShouldThrowExceptionIfInvalidNameIsGiven(string questionLabel)
        {
            var typeEnv = new QuestionEnvironment();
            Assert.Throws<ArgumentException>(() => typeEnv.IsLableDuplicate(questionLabel));
        }
    }
}
