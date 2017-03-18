using Questionnaires.Types;
using System.Collections.Generic;

namespace Questionnaires.SemanticAnalysis
{
    public class QLContext
    {
        private Dictionary<string, IType> Context = new Dictionary<string, IType>();

        public void AddQuestion(string name, IType type)
        {
            Context.Add(name, type);
        }

        public IType GetQuestionType(string name)
        {
            return Context[name];
        }

        public bool ContainsQuestion(string name)
        {
            return Context.ContainsKey(name);
        }
    }
}
