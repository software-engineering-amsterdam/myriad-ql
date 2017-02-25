namespace OffByOne.Qls.Ast.Style.Statements
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.Literals;

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
