namespace OffByOne.Ql.Evaluator
{
    using System.Collections.Generic;

    using OffByOne.Ql.Visitors.Contracts;

    public class QuestionVisitorTypeEnvironment : IContext
    {
        private readonly ISet<string> questionNames;
        private readonly ISet<string> questionLables;

        public QuestionVisitorTypeEnvironment()
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
