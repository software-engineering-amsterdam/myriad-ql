namespace OffByOne.LanguageCore.Ast.Style
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Style.Rules.Base;

    public class Section : AstNode
    {
        public Section(
            string name,
            ICollection<Rule> rules)
        {
            this.Name = name;
            this.Rules = rules;
        }

        public string Name { get; private set; }

        public ICollection<Rule> Rules { get; private set; }
    }
}
