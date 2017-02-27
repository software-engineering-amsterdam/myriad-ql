namespace OffByOne.Qls.Visitors.Base
{
    using MoreDotNet.Extensions.Collections;

    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.ValueTypes;
    using OffByOne.LanguageCore.Visitors.Contracts;
    using OffByOne.Qls.Ast.Style.Properties;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;
    using OffByOne.Qls.Ast.Style.Widgets;
    using OffByOne.Qls.Visitors.Contracts;

    public class BaseQlsVisitor<TResult, TContext>
        : ILiteralVisitor<TResult, TContext>,
        IValueTypeVisitor<TResult, TContext>,
        IPropertyVisitor<TResult, TContext>,
        IRuleVisitor<TResult, TContext>,
        IStatementVisitor<TResult, TContext>,
        IWidigetVisitor<TResult, TContext>
        where TContext : IContext
    {
        public virtual TResult Visit(IntegerLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DecimalLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(HexLiteral literal, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(IntegerValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(FloatValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(MoneyValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(BooleanValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(StringValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DateValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(VoidValueType valueType, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(ColorProperty expression, TContext context)
        {
            expression.Value.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(FontNameProperty expression, TContext context)
        {
            expression.Value.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(FontSizeProperty expression, TContext context)
        {
            expression.Value.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(FontStyleProperty expression, TContext context)
        {
            expression.Value.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(HeightProperty expression, TContext context)
        {
            expression.Value.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(WidthProperty expression, TContext context)
        {
            expression.Value.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(QuestionRule expression, TContext context)
        {
            expression.Properties?.ForEach(x => x.Accept(this, context));
            expression.Widget?.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(ValueTypeRule expression, TContext context)
        {
            expression.ValueType.Accept(this, context);
            expression.Properties.ForEach(x => x.Accept(this, context));
            expression.Widget.Accept(this, context);
            return default(TResult);
        }

        public virtual TResult Visit(Page expression, TContext context)
        {
            expression.Sections.ForEach(x => x.Accept(this, context));
            expression.ValueTypeRules.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public virtual TResult Visit(Section expression, TContext context)
        {
            expression.Name.Accept(this, context);
            expression.Sections.ForEach(x => x.Accept(this, context));
            expression.QuestionRules.ForEach(x => x.Accept(this, context));
            expression.ValueTypeRules.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public virtual TResult Visit(StyleSheet expression, TContext context)
        {
            expression.Pages.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public virtual TResult Visit(CheckBoxWidget expression, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(DropDownWidget expression, TContext context)
        {
            expression.Values.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public virtual TResult Visit(RadioButtonWidget expression, TContext context)
        {
            expression.Values.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public virtual TResult Visit(DefaultWidget expression, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(SliderWidget expression, TContext context)
        {
            expression.Values.ForEach(x => x.Accept(this, context));
            return default(TResult);
        }

        public virtual TResult Visit(SpinboxWidget expression, TContext context)
        {
            return default(TResult);
        }

        public virtual TResult Visit(TextFieldWidget expression, TContext context)
        {
            return default(TResult);
        }
    }
}
