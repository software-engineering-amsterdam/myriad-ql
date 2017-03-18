namespace OffByOne.Qls.Tests.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Checker.Analyzers;
    using OffByOne.Qls.Checker.Messages;

    using Xunit;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class TypeAnalyzerTests
    {
        public static IEnumerable<object[]> InvalidWidgetTypes => new List<object[]>
        {
            new object[]
            {
                new CheckBoxWidget(),
                new List<ValueType>
                {
                    new IntegerValueType(),
                    new MoneyValueType(),
                    new DecimalValueType(),
                    new StringValueType(),
                    new DateValueType()
                }
            },
            new object[]
            {
                new DropDownWidget(new List<StringLiteral>()),
                new List<ValueType>
                {
                    new IntegerValueType(),
                    new MoneyValueType(),
                    new DecimalValueType(),
                    new DateValueType()
                }
            },
            new object[]
            {
                new RadioButtonWidget(new List<StringLiteral>()),
                new List<ValueType>
                {
                    new IntegerValueType(),
                    new MoneyValueType(),
                    new DecimalValueType(),
                    new DateValueType()
                }
            },
            new object[]
            {
                new SliderWidget(new List<StringLiteral>()),
                new List<ValueType>
                {
                    new DateValueType(),
                    new StringValueType(),
                    new BooleanValueType()
                }
            },
            new object[]
            {
                new TextFieldWidget(),
                new List<ValueType>
                {
                    new DateValueType(),
                    new BooleanValueType()
                }
            },
            new object[]
            {
                new SpinboxWidget(),
                new List<ValueType>
                {
                    new DateValueType(),
                    new StringValueType(),
                    new BooleanValueType()
                }
            },
        };

        [Fact]
        public void CreatingNewObject_ShouldThrowExceptionWhenIncorectDataGiven()
        {
            Assert.Throws<ArgumentNullException>(() => new TypeAnalyzer(null));
        }

        [Fact]
        public void Analyze_ShouldThrowExceptionIfNullStyleRootNodeIsGiven()
        {
            var typeAnamyzer = new TypeAnalyzer();
            Assert.Throws<ArgumentNullException>(() => typeAnamyzer.Analyze(null, new Dictionary<string, ValueType>()));
        }

        [Fact]
        public void Analyze_ShouldThrowExceptionIfNullQuestionMappingsAreGivenNodeIsGiven()
        {
            var typeAnamyzer = new TypeAnalyzer();
            Assert.Throws<ArgumentNullException>(() => typeAnamyzer.Analyze(new StyleSheet("SampleStyleSheet"), null));
        }

        [Theory]
        [MemberData(nameof(InvalidWidgetTypes))]
        public void Analyze_ShouldDetectErrorIfInvalidQuestionRuleIsGiven(
            Widget widget,
            IList<ValueType> invalidTypes)
        {
            const string sampleQuestionName = "SampleQuestion";

            var questionRule = new QuestionRule(sampleQuestionName, widget, null);

            foreach (var invalidType in invalidTypes)
            {
                var typeAnamyzer = new TypeAnalyzer();

                var sampleQuestionMapings = new Dictionary<string, ValueType>
                {
                    { sampleQuestionName, invalidType }
                };

                typeAnamyzer.Analyze(this.GetStyleSheetWrapper(questionRule), sampleQuestionMapings);

                Assert.NotEmpty(typeAnamyzer.Report.AllMessages);
                Assert.IsType<InvalidWidgetTypeMessage>(typeAnamyzer.Report.Errors.FirstOrDefault());
                Assert.Equal(1, typeAnamyzer.Report.AllMessages.Count());
            }
        }

        [Theory]
        [MemberData(nameof(InvalidWidgetTypes))]
        public void Analyze_ShouldDetectErrorIfInvalidValueTypeRuleIsGiven(
            Widget widget,
            IList<ValueType> invalidTypes)
        {
            const string sampleQuestionName = "SampleQuestion";

            foreach (var invalidType in invalidTypes)
            {
                var typeAnamyzer = new TypeAnalyzer();

                var sampleQuestionMapings = new Dictionary<string, ValueType>
                {
                    { sampleQuestionName, invalidType }
                };

                var valueTypeRule = new ValueTypeRule(invalidType, widget, null);

                typeAnamyzer.Analyze(this.GetStyleSheetWrapper(valueTypeRule), sampleQuestionMapings);

                Assert.NotEmpty(typeAnamyzer.Report.AllMessages);
                Assert.IsType<InvalidWidgetTypeMessage>(typeAnamyzer.Report.Errors.FirstOrDefault());
                Assert.Equal(1, typeAnamyzer.Report.AllMessages.Count());
            }
        }

        private StyleSheet GetStyleSheetWrapper(QuestionRule rule)
        {
            return new StyleSheet("SampleStyleShett", new List<Page>
            {
                new Page("SamplePage", new List<Section>
                {
                    new Section(new StringLiteral("Sample Section"), null, new List<QuestionRule> { rule })
                })
            });
        }

        private StyleSheet GetStyleSheetWrapper(ValueTypeRule rule)
        {
            return new StyleSheet("SampleStyleShett", new List<Page>
            {
                new Page("SamplePage", null, new List<ValueTypeRule> { rule })
            });
        }
    }
}
