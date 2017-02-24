using Questionnaires.AST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

// TODO: find the right namespace
namespace Questionnaires
{
    public class QLContext
    {
        private Dictionary<string, QLType> Context = new Dictionary<string, QLType>();

        public void AddQuestion(string name, QLType type)
        {
            Context.Add(name, type);
        }

        public QLType GetQuestionType(string name)
        {
            // Todo: double check that this will throw when the quesion is not in the dict
            // Do we want to let the original error bubble up or create a new exception?
            return Context[name];
        }

        public bool ContainsQuestion(string name)
        {
            return Context.ContainsKey(name);
        }
    }
}
