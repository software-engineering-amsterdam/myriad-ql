namespace OffByOne.LanguageCore.Ast.Style
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast.Literals;
    using OffByOne.LanguageCore.Ast.Style.Rules.Base;

    public class Section : AstNode
    {
        public Section(
            StringLiteral name,
            ICollection<AstNode> nodes)
        {
            this.Name = name;
            this.Nodes = nodes;
        }

        public StringLiteral Name { get; private set; }

        public ICollection<AstNode> Nodes { get; private set; }
    }
}
