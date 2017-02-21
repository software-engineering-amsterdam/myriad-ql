namespace OffByOne.LanguageCore.Checker.Messages
{
    using OffByOne.LanguageCore.Ast;
    using OffByOne.LanguageCore.Ast.ValueTypes.Base;
    using OffByOne.LanguageCore.Checker.Messages.Base;

    public class InvaildTypeMessage : CheckerMessage
    {
        public InvaildTypeMessage(
            AstNode node,
            ValueType expected,
            ValueType actual,
            LogLevel level)
            : base($"Invalid type at: {node.SourceCode}. Expected {expected}, got {actual}", level)
        {
        }
    }
}
