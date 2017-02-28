namespace OffByOne.Ql.Checker.Messages
{
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Ql.Checker.Models;

    public class InvaildTypeMessage : CheckerMessage
    {
        public InvaildTypeMessage(
            AstNode node,
            ValueType expected,
            ValueType actual,
            LogLevel level = LogLevel.Error)
            : base($"Invalid type at: {node.SourceCode}. Expected {expected}, got {actual}", level)
        {
        }

        public InvaildTypeMessage(
            AstNode node,
            IEnumerable<ValueType> expected,
            ValueType actual,
            LogLevel level = LogLevel.Error)
            : base($"Invalid type at: {node.SourceCode}. Expected [{GetListStringValue(expected)}], got {actual}", level)
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
