namespace OffByOne.Ql.Checker.Messages
{
    using OffByOne.LanguageCore.Checker.Messages.Base;
    using OffByOne.LanguageCore.Checker.Models;
    using OffByOne.Ql.Ast.Expressions;

    public class UndeclaredVariableMessage : CheckerMessage
    {
        public UndeclaredVariableMessage(VariableExpression variable)
            : base($"Undeclared Variable {variable.Identifier} at: {variable.SourceCode}", LogLevel.Error)
        {
        }
    }
}
