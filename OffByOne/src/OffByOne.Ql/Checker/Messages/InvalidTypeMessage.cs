namespace OffByOne.Ql.Checker.Messages
{
    using System.Collections.Generic;
    using System.Linq;

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
            : base($"Invalid type at: {node.SourceCode}. Expected [{GetListStringValue(expected)}], got \"{actual}\"")
        {
        }

        // TODO: Move to a different place?
        private static string GetListStringValue(IEnumerable<ValueType> input)
        {
            return input
                .Select(x => x.ToString())
                .Aggregate((x, y) => x.ToString() + "," + y.ToString());
        }
    }
}
