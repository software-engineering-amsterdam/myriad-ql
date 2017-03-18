namespace OffByOne.Qls.Checker.Analyzers.Environment
{
    using System.Collections.Generic;

    using OffByOne.Qls.Checker.Analyzers.Environment.Contracts;

    public class FormAnalyzerEnvironment : IFormAnalyzerEnvironment
    {
        public FormAnalyzerEnvironment()
        {
            this.StyleSheetNames = new HashSet<string>();
            this.PageLabels = new HashSet<string>();
            this.SectionNames = new HashSet<string>();
            this.QuestionIdentifier = new HashSet<string>();
        }

        public ISet<string> StyleSheetNames { get; set; }

        public ISet<string> PageLabels { get; }

        public ISet<string> SectionNames { get; }

        public ISet<string> QuestionIdentifier { get; }
    }
}