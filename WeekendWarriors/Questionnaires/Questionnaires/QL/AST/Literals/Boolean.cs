using Questionnaires.Compilation;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;
using System.Collections.Generic;

namespace Questionnaires.QL.AST.Literals
{
    public class Boolean : IExpression
    {
        public Boolean(bool value)
        {
            this.Value = value;
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

        public IType GetResultType(QLContext context)
        {
            return new BooleanType();
        }
    }
}
