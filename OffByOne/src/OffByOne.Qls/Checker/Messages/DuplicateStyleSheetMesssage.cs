namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Ql.Checker.Models;
    using OffByOne.Qls.Ast.Style.Statements;

    public class DuplicateStyleSheetMesssage : CheckerMessage
    {
        public DuplicateStyleSheetMesssage(StyleSheet styleSheet, LogLevel level = LogLevel.Error)
            : base($"Duplicate stylesheet name {styleSheet.Id} at {styleSheet.SourceCode}", level)
        {
        }
    }
}
