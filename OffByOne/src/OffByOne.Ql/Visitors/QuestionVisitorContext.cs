namespace OffByOne.Ql.Visitors
{
    using System.Collections.Generic;

    using OffByOne.LanguageCore.Visitors.Contracts;

    public class QuestionVisitorContext : IContext
    {
        private readonly ISet<string> questionNames;
        private readonly ISet<string> questionLables;

        public QuestionVisitorContext()
        {
            this.questionLables = new HashSet<string>();
            this.questionNames = new HashSet<string>();
        }

        public void AddQuestionName(string name)
        {
            this.questionNames.Add(name);
        }

        public void AddQuestionLabel(string label)
        {
            this.questionLables.Add(label);
        }

        public bool IsNameDuplicate(string name)
        {
            return this.questionNames.Contains(name);
        }

        public bool IsLableDuplicate(string label)
        {
            return this.questionLables.Contains(label);
        }
    }
}
