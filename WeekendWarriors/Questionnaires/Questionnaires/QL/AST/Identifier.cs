using Questionnaires.Compilation;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;
using System.Collections.Generic;

namespace Questionnaires.QL.AST
{
    public class Identifier : IExpression
    {
        public Identifier(string name)
        {
            this.Name = name;
        }

        public string Name
        {
            get;
        }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            if (!context.ContainsQuestion(Name))
            {
                messages.Add(new Error(string.Format("Invalid use of undefined identifier {0}", Name)));
                return false;
            }

            return true;
        }

        public IType GetResultType(QLContext context)
        {
            return context.GetQuestionType(Name);
        }
    }
}
