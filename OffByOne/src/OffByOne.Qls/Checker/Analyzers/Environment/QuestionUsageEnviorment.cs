namespace OffByOne.Qls.Checker.Analyzers.Environment
{
    using System.Collections.Generic;
    using System.Linq;

    using OffByOne.Qls.Checker.Analyzers.Environment.Contracts;

    public class QuestionUsageEnviorment : IQuestionUsageEnviorment
    {
        private readonly IDictionary<string, bool> usageMap;

        public QuestionUsageEnviorment()
        {
            this.usageMap = new Dictionary<string, bool>();
        }

        public bool HasUnusedQuestions => this.usageMap.Any(x => !x.Value);

        public IEnumerable<string> UnusedQuestions => this.usageMap
            .Where(x => !x.Value)
            .Select(x => x.Key);

        public void MarkQuestionAsUsed(string question)
        {
            this.usageMap[question] = true;
        }

        public void AddQuestion(string question)
        {
            this.usageMap[question] = false;
        }

        public bool HasQuestion(string question)
        {
            return this.usageMap.ContainsKey(question);
        }
    }
}
