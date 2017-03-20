namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Checker.Messages.Base;

    /// <summary>
    /// A message representing a undeclared variable found during the static code analysis.
    /// </summary>
    /// <seealso cref="OffByOne.Ql.Checker.Messages.Base.ErrorMessage" />
    public class UndeclaredVariableMessage : ErrorMessage
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="UndeclaredVariableMessage"/> class.
        /// </summary>
        /// <param name="variable">The variable.</param>
        public UndeclaredVariableMessage(VariableExpression variable)
            : base($"Undeclared Variable \"{variable.Identifier}\" at: {variable.SourceCode}")
        {
        }
    }
}
