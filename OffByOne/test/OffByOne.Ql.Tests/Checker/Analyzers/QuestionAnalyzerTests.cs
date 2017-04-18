namespace OffByOne.Ql.Tests.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.Literals;
    using OffByOne.Ql.Ast.Statements;
    using OffByOne.Ql.Ast.Statements.Base;
    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Checker.Analyzers;
    using OffByOne.Ql.Checker.Messages;

    using Xunit;

    public class QuestionAnalyzerTests
    {
        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new QuestionAnalyzer(null));
        }

        [Fact]
        public void Analyze_ShouldDetectDuplicateQuestions()
        {
            var duplicateQuestionForm = new FormStatement("DuplicateQuestionForm", new List<Statement>
            {
                new QuestionStatement("duplicate", new StringValueType(), new StringLiteral("Sample")),
                new QuestionStatement("duplicate", new StringValueType(), new StringLiteral("SampleTwo")),
            });

            var questionAnalyzer = new QuestionAnalyzer();

            questionAnalyzer.Analyze(duplicateQuestionForm);
            Assert.NotEmpty(questionAnalyzer.Report.Errors);
            Assert.Equal(1, questionAnalyzer.Report.Errors.Count());
            Assert.IsType<DuplicateQuestionIdentifierMessage>(questionAnalyzer.Report.Errors.FirstOrDefault());
        }

        [Fact]
        public void Analyze_ShouldDetectDuplicateQuestionLabels()
        {
            var duplicateQuestionForm = new FormStatement("DuplicateQuestionForm", new List<Statement>
            {
                new QuestionStatement("duplicate", new StringValueType(), new StringLiteral("Sample")),
                new QuestionStatement("duplicate1", new StringValueType(), new StringLiteral("Sample")),
            });

            var questionAnalyzer = new QuestionAnalyzer();

            questionAnalyzer.Analyze(duplicateQuestionForm);
            Assert.NotEmpty(questionAnalyzer.Report.Warnings);
            Assert.Equal(1, questionAnalyzer.Report.Warnings.Count());
            Assert.IsType<DuplicateQuestionLabelMessage>(questionAnalyzer.Report.Warnings.FirstOrDefault());
        }
    }
}
