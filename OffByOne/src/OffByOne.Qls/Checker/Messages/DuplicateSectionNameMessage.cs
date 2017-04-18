namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Qls.Ast.Style.Statements;

    public class DuplicateSectionNameMessage : WarningMessage
    {
        public DuplicateSectionNameMessage(Section section)
            : base($"Duplicate section name \"{section.Name.Value}\" at: {section.SourceCode}")
        {
        }
    }
}
