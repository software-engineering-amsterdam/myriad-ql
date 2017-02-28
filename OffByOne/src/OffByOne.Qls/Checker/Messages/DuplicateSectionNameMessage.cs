namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Ql.Checker.Models;
    using OffByOne.Qls.Ast.Style.Statements;

    public class DuplicateSectionNameMessage : CheckerMessage
    {
        public DuplicateSectionNameMessage(Section section, LogLevel level = LogLevel.Warning)
            : base($"Duplicate section name {section.Name.Value} at {section.SourceCode}", level)
        {
        }
    }
}
