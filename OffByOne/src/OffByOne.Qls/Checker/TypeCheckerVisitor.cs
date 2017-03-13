namespace OffByOne.Qls.Checker
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Checker;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Checker.Messages;
    using OffByOne.Qls.Visitors.Base;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class TypeCheckerVisitor : BaseQlsVisitor<object, TypeCheckerEnvironment>
    {
        private readonly IDictionary<string, ValueType> qlQuestionMappings;

        private IDictionary<Type, ICollection<ValueType>> widgetTypeRules = new Dictionary<Type, ICollection<ValueType>>()
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

        public TypeCheckerVisitor(IDictionary<string, ValueType> qlQuestionMappings)
            : this(qlQuestionMappings, new CheckerReport())
        {
            this.qlQuestionMappings = qlQuestionMappings;
        }

        public TypeCheckerVisitor(IDictionary<string, ValueType> qlQuestionMappings, CheckerReport report)
        {
            this.qlQuestionMappings = qlQuestionMappings;
            this.Report = report;
        }

        public CheckerReport Report { get; }

        public IDictionary<Type, ICollection<ValueType>> WidgetTypeRules
        {
            get { return this.widgetTypeRules; }
            set { this.widgetTypeRules = value; }
        }

        public override object Visit(QuestionRule expression, TypeCheckerEnvironment environment)
        {
            var questionType = this.qlQuestionMappings[expression.Name];
            this.CheckWidgetTyping(expression.Widget, questionType);

            return base.Visit(expression, environment);
        }

        public override object Visit(ValueTypeRule expression, TypeCheckerEnvironment environment)
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
