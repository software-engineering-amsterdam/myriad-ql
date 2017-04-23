namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Checker.Messages.Base;

    public class UndeclaredVariableMessage : ErrorMessage
    {
        public UndeclaredVariableMessage(VariableExpression variable)
            : base($"Undeclared Variable \"{variable.Identifier}\" at: {variable.SourceCode}")
        {
        }
    }
}
