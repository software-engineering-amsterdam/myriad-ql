namespace OffByOne.Ql.Checker.Analyzers.Environment
{
    using System;
    using System.Collections.Generic;

    using MoreDotNet.Wrappers;

    using OffByOne.Ql.Common.Visitors.Contracts;

    public class QuestionVisitorTypeEnvironment : IEnvironment
    {
        private readonly ISet<string> questionNames;
        private readonly ISet<string> questionLables;

        public QuestionVisitorTypeEnvironment()
        {
            this.questionLables = new HashSet<string>();
            this.questionNames = new HashSet<string>();
        }

        public void AddQuestionIdentifier(string name)
        {
            if (name.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Question identifier must not be empty or null.",
                    nameof(name));
            }

            this.questionNames.Add(name);
        }

        public void AddQuestionLabel(string label)
        {
            if (label.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Question label must not be empty or null.",
                    nameof(label));
            }

            this.questionLables.Add(label);
        }

        public bool IsIdentifierDuplicate(string name)
        {
            if (name.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Question identifier must not be empty or null.",
                    nameof(name));
            }

            return this.questionNames.Contains(name);
        }

        public bool IsLableDuplicate(string label)
        {
            if (label.IsNullOrWhiteSpace())
            {
                throw new ArgumentException(
                    "Question label must not be empty or null.",
                    nameof(label));
            }

            return this.questionLables.Contains(label);
        }
    }
}
