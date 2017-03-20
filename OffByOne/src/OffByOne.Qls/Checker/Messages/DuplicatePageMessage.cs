namespace OffByOne.Qls.Checker.Messages
{
    using OffByOne.Ql.Checker.Messages.Base;
    using OffByOne.Qls.Ast.Style.Statements;

    public class DuplicatePageMessage : ErrorMessage
    {
        public DuplicatePageMessage(Page page)
            : base($"Duplicate page label \"{page.Id}\" at: {page.SourceCode}")
        {
        }
    }
}
