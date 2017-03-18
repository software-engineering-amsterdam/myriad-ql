namespace OffByOne.Qls.Common.Visitors.Base
{
    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Common.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Properties;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Common.Visitors.Contracts;

    public class BaseQlsVisitor<TResult, TEnvironment>
        : ILiteralVisitor<TResult, TEnvironment>,
        IValueTypeVisitor<TResult, TEnvironment>,
        IPropertyVisitor<TResult, TEnvironment>,
        IRuleVisitor<TResult, TEnvironment>, Contracts.IStatementVisitor<TResult, TEnvironment>,
        IWidigetVisitor<TResult, TEnvironment>
        where TEnvironment : IEnvironment
    {
        public virtual TResult Visit(IntegerLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(HexLiteral literal, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(IntegerValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(VoidValueType valueType, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(ColorProperty property, TEnvironment environment)
        {
            property.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(FontNameProperty property, TEnvironment environment)
        {
            property.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(FontSizeProperty property, TEnvironment environment)
        {
            property.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(FontStyleProperty property, TEnvironment environment)
        {
            property.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(HeightProperty property, TEnvironment environment)
        {
            property.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(WidthProperty property, TEnvironment environment)
        {
            property.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(QuestionRule rule, TEnvironment environment)
        {
            rule.Properties?.ForEach(x => x.Accept(this, environment));
            rule.Widget?.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(ValueTypeRule rule, TEnvironment environment)
        {
            rule.ValueType.Accept(this, environment);
            rule.Properties.ForEach(x => x.Accept(this, environment));
            rule.Widget.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(Page statement, TEnvironment environment)
        {
            statement.Sections.ForEach(x => x.Accept(this, environment));
            statement.ValueTypeRules.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(Section statement, TEnvironment environment)
        {
            statement.Name.Accept(this, environment);
            statement.Sections.ForEach(x => x.Accept(this, environment));
            statement.QuestionRules.ForEach(x => x.Accept(this, environment));
            statement.ValueTypeRules.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(StyleSheet statement, TEnvironment environment)
        {
            statement.Pages.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(CheckBoxWidget widget, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DropDownWidget widget, TEnvironment environment)
        {
            widget.Values.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(RadioButtonWidget widget, TEnvironment environment)
        {
            widget.Values.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(DefaultWidget widget, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(SliderWidget widget, TEnvironment environment)
        {
            widget.Values.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(SpinboxWidget widget, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(TextFieldWidget widget, TEnvironment environment)
        {
            return default(TResult);
        }
    }
}
