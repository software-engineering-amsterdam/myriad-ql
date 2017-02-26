using Questionnaires.AST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.SemanticAnalysis
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
            return Context[name];
        }

        public bool ContainsQuestion(string name)
        {
            return Context.ContainsKey(name);
        }
    }
}
