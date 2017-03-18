namespace OffByOne.Qls.Checker.Analyzers.Environment
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Ast.Style.Widgets.Base;
    using OffByOne.Qls.Checker.Analyzers.Environment.Contracts;

    using ValueType = OffByOne.Ql.Ast.ValueTypes.Base.ValueType;

    public class TypeAnalyzerEnvironment : ITypeAnalyzerEnvironment
    {
        private readonly IDictionary<Type, ICollection<ValueType>> widgetTypeRules = new Dictionary<Type, ICollection<ValueType>>
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
                    new IntegerValueType(),
                    new MoneyValueType(),
                    new DecimalValueType()
                }
            }
        };

        public bool IsWidgetTypingCorrect(Widget widget, ValueType questionType)
        {
            var expectedTypes = this.widgetTypeRules[widget.GetType()];
            return expectedTypes.Any(x => x == questionType);
        }

        public IEnumerable<ValueType> GetCorrectTypesForWidget(Widget widget)
        {
            return this.widgetTypeRules[widget.GetType()];
        }
    }
}
