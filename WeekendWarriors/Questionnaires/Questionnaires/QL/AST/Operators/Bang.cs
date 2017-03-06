using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.Messages
;
using System.Diagnostics;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Value;

namespace Questionnaires.QL.AST.Operators
{
    public class Bang : Unary
    {
        public Bang(IExpression operand) : base(operand)
        {
            
        }

        public override IValue GetResultType(QLContext context)
        {
            return Operand.GetResultType(context).Bang();
        }
    }
}
