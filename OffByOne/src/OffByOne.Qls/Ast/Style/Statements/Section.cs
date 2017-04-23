namespace OffByOne.Qls.Ast.Style.Statements
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Wrappers;

    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements.Base;
    using OffByOne.Qls.Common.Visitors.Contracts;

    using StringLiteral = OffByOne.Qls.Ast.Style.Literals.StringLiteral;

    public class Section : Statement
    {
        public Section(
            StringLiteral name,
            IEnumerable<Section> sections = null,
            IEnumerable<QuestionRule> questionRules = null,
            IEnumerable<ValueTypeRule> valueTypeRules = null)
        {
            if (name == null)
            {
                throw new ArgumentNullException(nameof(name));
            }

            if (name.Value.IsNullOrWhiteSpace())
            {
                throw new ArgumentException("A non-null, non-empty section name must be given.");
            }

            this.Name = name;
            this.Sections = sections;
            this.QuestionRules = questionRules;
            this.ValueTypeRules = valueTypeRules;
        }

        public StringLiteral Name { get; }

        public IEnumerable<Section> Sections { get; set; }

        public IEnumerable<QuestionRule> QuestionRules { get; set; }

        public IEnumerable<ValueTypeRule> ValueTypeRules { get; set; }

        public override TResult Accept<TResult, TContext>(
            IStatementVisitor<TResult, TContext> visitor,
            TContext environment)
        {
            return visitor.Visit(this, environment);
        }
    }
}
