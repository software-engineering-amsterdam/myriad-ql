namespace OffByOne.Ql.Checker
{
    using System;

    using OffByOne.LanguageCore.Checker;
    using OffByOne.LanguageCore.Visitors;
    using OffByOne.Ql.Ast.Statements;

    public class TypeChecker
    {
        // TODO: Extract interface!
        private readonly TypeCheckerVisitor visitor;

        public TypeChecker()
            : this(new TypeCheckerVisitor())
        {
        }

        public TypeChecker(TypeCheckerVisitor visitor)
        {
            if (visitor == null)
            {
                throw new ArgumentNullException(nameof(visitor), "A valid visitor must provided.");
            }

            this.visitor = visitor;
        }

        public CheckerReport Check(FormStatement node)
        {
            this.visitor.Visit(node, new VisitorContext());
            return this.visitor.Report;
        }
    }
}
