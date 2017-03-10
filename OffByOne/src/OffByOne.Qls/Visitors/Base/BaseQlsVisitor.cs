namespace OffByOne.Qls.Visitors.Base
{
    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast.ValueTypes;
    using OffByOne.Ql.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Literals;
    using OffByOne.Qls.Ast.Style.Properties;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Visitors.Contracts;

    public class BaseQlsVisitor<TResult, TEnvironment>
        : ILiteralVisitor<TResult, TEnvironment>,
        IValueTypeVisitor<TResult, TEnvironment>,
        IPropertyVisitor<TResult, TEnvironment>,
        IRuleVisitor<TResult, TEnvironment>, 
        Contracts.IStatementVisitor<TResult, TEnvironment>,
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

        public virtual TResult Visit(ColorProperty expression, TEnvironment environment)
        {
            expression.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(FontNameProperty expression, TEnvironment environment)
        {
            expression.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(FontSizeProperty expression, TEnvironment environment)
        {
            expression.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(FontStyleProperty expression, TEnvironment environment)
        {
            expression.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(HeightProperty expression, TEnvironment environment)
        {
            expression.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(WidthProperty expression, TEnvironment environment)
        {
            expression.Value.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(QuestionRule expression, TEnvironment environment)
        {
            expression.Properties?.ForEach(x => x.Accept(this, environment));
            expression.Widget?.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(ValueTypeRule expression, TEnvironment environment)
        {
            expression.ValueType.Accept(this, environment);
            expression.Properties.ForEach(x => x.Accept(this, environment));
            expression.Widget.Accept(this, environment);
            return default(TResult);
        }

        public virtual TResult Visit(Page expression, TEnvironment environment)
        {
            expression.Sections.ForEach(x => x.Accept(this, environment));
            expression.ValueTypeRules.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(Section expression, TEnvironment environment)
        {
            expression.Name.Accept(this, environment);
            expression.Sections.ForEach(x => x.Accept(this, environment));
            expression.QuestionRules.ForEach(x => x.Accept(this, environment));
            expression.ValueTypeRules.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(StyleSheet expression, TEnvironment environment)
        {
            expression.Pages.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(CheckBoxWidget expression, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DropDownWidget expression, TEnvironment environment)
        {
            expression.Values.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(RadioButtonWidget expression, TEnvironment environment)
        {
            expression.Values.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(DefaultWidget expression, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(SliderWidget expression, TEnvironment environment)
        {
            expression.Values.ForEach(x => x.Accept(this, environment));
            return default(TResult);
        }

        public virtual TResult Visit(SpinboxWidget expression, TEnvironment environment)
        {
            return default(TResult);
        }

        public virtual TResult Visit(TextFieldWidget expression, TEnvironment environment)
        {
            return default(TResult);
        }
    }
}
