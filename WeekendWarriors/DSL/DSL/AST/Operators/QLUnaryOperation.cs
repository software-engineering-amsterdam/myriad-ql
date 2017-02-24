using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DSL.SemanticAnalysis.SemenaticAnalysisEvents;

namespace DSL.AST
{
    public enum QLUnaryOperator
    {
        Bang,
        Plus,
        Minus
    }

    public abstract class QLUnaryOperation : IQLExpression
    {
        public QLUnaryOperation(IQLExpression operand)
        {
            this.Operand = operand;
        }

        public IQLExpression Operand
        {
            get;
        }

        public abstract QLType? CheckTypes(List<QLType> parameters, QLContext context, List<ISemenaticAnalysisEvent> events);
    }
}
