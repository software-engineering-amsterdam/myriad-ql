namespace OffByOne.Ql.Checker.Messages
{
    using System.Collections.Generic;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Ast;
    using OffByOne.Ql.Ast.ValueTypes.Base;
    using OffByOne.Ql.Checker.Messages.Base;

    /// <summary>
    /// A message representing a invalid type found during the static code analysis.
    /// </summary>
    /// <seealso cref="OffByOne.Ql.Checker.Messages.Base.ErrorMessage" />
    public class InvalidTypeMessage : ErrorMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="InvalidTypeMessage"/> class.
        /// </summary>
        /// <param name="node">The node at which the problem was found.</param>
        /// <param name="expected">The expected type.</param>
        /// <param name="actual">The actual type.</param>
        public InvalidTypeMessage(
            AstNode node,
            ValueType expected,
            ValueType actual)
            : base($"Invalid type at: {node.SourceCode}. Expected \"{expected}\", got \"{actual}\"")
        {
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="InvalidTypeMessage"/> class.
        /// </summary>
        /// <param name="node">The node at which the problem was found.</param>
        /// <param name="expected">The expected types.</param>
        /// <param name="actual">The actual type.</param>
        public InvalidTypeMessage(
            AstNode node,
            IEnumerable<ValueType> expected,
            ValueType actual)
            : base($"Invalid type at: {node.SourceCode}. Expected [{expected.ToString(x => x.ToString(), ",")}], got \"{actual}\"")
        {
        }
    }
}
