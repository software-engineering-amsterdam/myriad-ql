namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Qls.Ast.Style.Statements;

    public class DuplicateStyleSheetMesssage : ErrorMessage
    {
        public DuplicateStyleSheetMesssage(StyleSheet styleSheet)
            : base($"Duplicate stylesheet name \"{styleSheet.Id}\" at: {styleSheet.SourceCode}")
        {
        }
    }
}
