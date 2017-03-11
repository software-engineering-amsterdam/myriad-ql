namespace OffByOne.Qls.Checker
{
    using System.Collections.Generic;

    using OffByOne.Ql.Visitors.Contracts;

    public class FormCheckerEnvironment : IEnvironment
    {
        public FormCheckerEnvironment()
        {
            this.StyleSheetNames = new HashSet<string>();
            this.PageLabels = new HashSet<string>();
            this.SectionNames = new HashSet<string>();
            this.QuestionLabels = new HashSet<string>();
        }

        public ISet<string> StyleSheetNames { get; set; }

        public ISet<string> PageLabels { get; }

        public ISet<string> SectionNames { get; }

        public ISet<string> QuestionLabels { get; }
    }
}