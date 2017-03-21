using Questionnaires.ErrorHandling;
using Questionnaires.QL.SemanticAnalysis;
using Questionnaires.QL.AST.Types;
using System.Collections.Generic;

namespace Questionnaires.QL.AST.Literals
{
    public class Boolean : IExpression
    {
        public Boolean(bool value)
        {
            Value = value;
        }

        public bool Value
        {
            get;
        }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // Nothing to check for bool literal
            return true;
        }

        public void GetIdentifiers(HashSet<Identifier> identifiers)
        {
            // No identifiers here
        }

        public IType GetResultType(QLContext context)
        {
            return new BooleanType();
        }
    }
}
