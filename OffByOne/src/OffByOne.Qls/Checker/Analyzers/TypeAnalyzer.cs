namespace OffByOne.Qls.Checker.Analyzers
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Checker;
    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Checker.Analyzers.Contracts;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class TypeAnalyzer : BaseQlsVisitor<object, TypeAnalyzerEnvironment>, IAnalyzer
    {
        private IDictionary<string, ValueType> qlQuestionMappings;

        private IDictionary<Type, ICollection<ValueType>> widgetTypeRules = new Dictionary<Type, ICollection<ValueType>>
        {
            {
                typeof(DropDownWidget), new List<ValueType>
                {
                    new StringValueType(),
                    new BooleanValueType()
                }
            },
            {
                typeof(CheckBoxWidget), new List<ValueType>
                {
                    new BooleanValueType()
                }
            },
            {
                typeof(RadioButtonWidget), new List<ValueType>
                {
                    new StringValueType(),
                    new BooleanValueType()
                }
            },
            {
                typeof(SliderWidget), new List<ValueType>
                {
                    new DecimalValueType(),
                    new IntegerValueType(),
                    new MoneyValueType()
                }
            },
            {
                typeof(TextFieldWidget), new List<ValueType>
                {
                    new StringValueType(),
                    new DecimalValueType(),
                    new IntegerValueType(),
                    new MoneyValueType()
                }
            },
            {
                typeof(SpinboxWidget), new List<ValueType>
                {
                    new StringValueType(),
                    new BooleanValueType()
                }
            }
        };

        public TypeAnalyzer()
            : this(new CheckerReport())
        {
        }

        public TypeAnalyzer(ICheckerReport report)
        {
            this.Report = report;
        }

        public ICheckerReport Report { get; }

        public IDictionary<Type, ICollection<ValueType>> WidgetTypeRules
        {
            get { return this.widgetTypeRules; }
            set { this.widgetTypeRules = value; }
        }

        public void Analyze(StyleSheet root, IDictionary<string, ValueType> questionMappings)
        {
            this.qlQuestionMappings = questionMappings;
            this.Visit(root, new TypeAnalyzerEnvironment());
        }

        public override object Visit(QuestionRule expression, TypeAnalyzerEnvironment environment)
        {
            var questionType = this.qlQuestionMappings[expression.Name];
            this.CheckWidgetTyping(expression.Widget, questionType);

            return base.Visit(expression, environment);
        }

        public override object Visit(ValueTypeRule expression, TypeAnalyzerEnvironment environment)
        {
            this.CheckWidgetTyping(expression.Widget, expression.ValueType);

            return base.Visit(expression, environment);
        }

        private void CheckWidgetTyping(Widget widget, ValueType questionType)
        {
            var expectedTypes = this.widgetTypeRules[widget.GetType()];
            if (expectedTypes.All(x => x != questionType))
            {
                this.Report.Add(new InvalidWidgetTypeMessage(
                    widget,
                    expectedTypes,
                    questionType));
            }
        }
    }
}
