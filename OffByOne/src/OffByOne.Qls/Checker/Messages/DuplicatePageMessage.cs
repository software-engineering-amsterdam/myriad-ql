namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.LanguageCore.Checker.Messages.Base;
    using OffByOne.LanguageCore.Checker.Models;
    using OffByOne.Qls.Ast.Style.Rules;
    using OffByOne.Qls.Ast.Style.Statements;

    public class DuplicatePageMessage : CheckerMessage
    {
        public DuplicatePageMessage(Page page, LogLevel level = LogLevel.Error)
            : base($"Duplicate page label {page.Id} at {page.SourceCode}", level)
        {
        }
    }
}
