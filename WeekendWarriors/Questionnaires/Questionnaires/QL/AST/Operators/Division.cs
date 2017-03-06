using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.SemanticAnalysis.Messages;
using Questionnaires.Value;

namespace Questionnaires.QL.AST.Operators
{
    public class Division : Binary
    {
        public Division(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {

        }

        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).Divide(Rhs.GetResultType(context));
        }
    }
}
