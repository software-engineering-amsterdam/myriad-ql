namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.Ql.Ast.Expressions;
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Ql.Checker.Models;

    public class UndeclaredVariableMessage : CheckerMessage
    {
        public UndeclaredVariableMessage(VariableExpression variable)
            : base($"Undeclared Variable {variable.Identifier} at: {variable.SourceCode}", LogLevel.Error)
        {
        }
    }
}
