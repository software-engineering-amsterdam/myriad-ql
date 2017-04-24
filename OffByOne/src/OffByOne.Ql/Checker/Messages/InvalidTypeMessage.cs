namespace OffByOne.Ql.Checker.Messages
{
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Messages.Base;

    public class InvalidTypeMessage : ErrorMessage
    {
        public InvalidTypeMessage(
            AstNode node,
            ValueType expected,
            ValueType actual)
            : base($"Invalid type at: {node.SourceCode}. Expected \"{expected}\", got \"{actual}\"")
        {
        }

        public InvalidTypeMessage(
            AstNode node,
            IEnumerable<ValueType> expected,
            ValueType actual)
            : base($"Invalid type at: {node.SourceCode}. Expected [{expected.ToString(x => x.ToString(), ",")}], got \"{actual}\"")
        {
        }
    }
}
